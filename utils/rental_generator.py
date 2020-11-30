#!/bin/python3
import hashlib
import pysodium
import sqlite3
import re
import os

from faker import Faker

faker = Faker('pt_BR')

default_password = 'password123'

conn = sqlite3.connect('tmp.db')

def prepare_database():
    f_table = open('sql/create_tables.sql')
    statements = f_table.read().split(';')
    for statement in statements:
        conn.execute(statement)
    f_table.close()

    f_cities = open('sql/insert_cities.sql')
    statements = f_cities.read().split(';')
    for statement in statements:
        conn.execute(statement)
    f_cities.close()

    conn.commit()

def generate_random_rental(conn):
    name = faker.company()
    cnpj_with_dots = faker.cnpj()
    cnpj = re.sub("\D","",cnpj_with_dots)
    email = faker.email()
    description = faker.paragraph(nb_sentences=3)
    postal_code = faker.postcode(False)
    street_name = faker.street_name()
    neighborhood = faker.neighborhood()
    street_number = faker.building_number()

    cursor = conn.execute('SELECT * FROM city ORDER BY RANDOM() LIMIT 1 ;')
    rows = cursor.fetchall()
    (city, state) = rows[0]

    saltBytes = pysodium.randombytes(pysodium.crypto_pwhash_SALTBYTES)
    opslimit = pysodium.crypto_pwhash_argon2id_OPSLIMIT_INTERACTIVE
    memlimit = pysodium.crypto_pwhash_argon2id_MEMLIMIT_INTERACTIVE
    alg = pysodium.crypto_pwhash_ALG_ARGON2ID13

    passwordBytes = pysodium.crypto_pwhash(64, alg=alg, passwd=default_password, salt=saltBytes, opslimit=opslimit, memlimit=memlimit)
    password = passwordBytes.hex()
    salt = saltBytes.hex()

    return (name,cnpj,email,description,postal_code,street_name,neighborhood,street_number, city, state, password,salt)



prepare_database()
f = open("sql/insert_rentals.sql", "w")
for i in range(40):
    print( f'{i + 1}/{40}')
    (name,cnpj,email,description,postal_code,street_name,neighborhood,street_number, city, state,password,salt) = generate_random_rental(conn)

    f.write(f'INSERT INTO rental (name,cnpj,email,description,postal_code,street_name,neighborhood,street_number, fk_city_name, fk_city_state,password,salt) VALUES ("{name}","{cnpj}","{email}","{description}","{postal_code}","{street_name}","{neighborhood}","{street_number}", "{city}", "{state}", "{password}", "{salt}");\n')

f.close()
conn.close()

os.remove('tmp.db')
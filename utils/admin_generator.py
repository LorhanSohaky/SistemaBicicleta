#!/bin/python3
import hashlib
import pysodium
import sqlite3
import re
import os

from faker import Faker

faker = Faker('pt_BR')

default_password = 'password123'

admins = [
    {
        'email':'admin1@mailinator.com',
        'name':'Lorhan Sohaky'
    }, 
    {
        'email':'admin2@mailinator.com',
        'name':'Lucas Martins'
        }
]

def hash_default_password():
    saltBytes = pysodium.randombytes(pysodium.crypto_pwhash_SALTBYTES)
    opslimit = pysodium.crypto_pwhash_argon2id_OPSLIMIT_INTERACTIVE
    memlimit = pysodium.crypto_pwhash_argon2id_MEMLIMIT_INTERACTIVE
    alg = pysodium.crypto_pwhash_ALG_ARGON2ID13

    passwordBytes = pysodium.crypto_pwhash(64, alg=alg, passwd=default_password, salt=saltBytes, opslimit=opslimit, memlimit=memlimit)
    password = passwordBytes.hex().upper()
    salt = saltBytes.hex().upper()

    return (password,salt)



f = open("sql/insert_admins.sql", "w")
i = 1
for admin in admins:
    print( f'{i}/{len(admins)}')
    (password,salt) = hash_default_password()

    f.write(f'INSERT INTO admin (name, email, password, salt) VALUES ("{admin["name"]}","{admin["email"]}", "{password}", "{salt}");\n')
    i += 1

f.close()

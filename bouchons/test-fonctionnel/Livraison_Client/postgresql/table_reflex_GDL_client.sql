--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.2
-- Dumped by pg_dump version 9.3.1
-- Started on 2015-12-22 12:13:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


--
-- TOC entry 3005 (class 0 OID 18057)
-- Dependencies: 206
-- Data for Name: reflex_groupe_de_lieu; Type: TABLE DATA; Schema: stif_rec2; Owner: pguser
--


DELETE FROM reflex_zone_d_embarquement;
DELETE FROM reflex_zone_de_lieu;
DELETE FROM reflex_lieu_d_arret;
DELETE FROM reflex_groupe_de_lieu;


INSERT INTO reflex_groupe_de_lieu (id, id_ref_a, id_version, nom) VALUES (16, '415732', '657753', 'Aéroport Roissy Charles de Gaulle');
INSERT INTO reflex_groupe_de_lieu (id, id_ref_a, id_version, nom) VALUES (17, '415730', '657747', 'Gare Montparnasse');
INSERT INTO reflex_groupe_de_lieu (id, id_ref_a, id_version, nom) VALUES (18, '415731', '657750', 'Aéroport d''Orly');



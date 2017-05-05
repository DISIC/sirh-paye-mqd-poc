-- Jeu de données du TEST-56
-- Vidage des tables
BEGIN;

DELETE FROM codifligne;

-- Ligne inchangée
INSERT INTO codifligne(id, id_ref_lig_c, transporteur1, transporteur2, nom, nom_court, mode, sous_mode, code_stif, id_ref_lig_a)
VALUES ('1', 'C01727', 'SNCF', '','RER C', 'RER C', 'Fer', '', '800803', '');

-- Ligne modifiée
INSERT INTO codifligne(id, id_ref_lig_c, transporteur1, transporteur2, nom, nom_court, mode, sous_mode, code_stif, id_ref_lig_a)
VALUES ('2', 'C00188', 'Keolis', '','89', '89', 'Fer', '', '13013089', 'A00171');

-- Ligne supprimée
INSERT INTO codifligne(id, id_ref_lig_c, transporteur1, transporteur2, nom, nom_court, mode, sous_mode, code_stif, id_ref_lig_a)
VALUES ('3', 'C01', 'Athis Car', '','Licorne', 'Licorne', 'Bus', '', '800803', 'A00045');

COMMIT;




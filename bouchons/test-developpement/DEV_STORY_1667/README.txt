STIFSCRUM-1667 - Rapport 10

En tant que RELAIS, je veux communiquer en https avec les transporteurs indépendants

---------- Configuration SOAPUI ----------

1 - Aller dans le menu File/Preferences
2 - Cliquer "SSL Settings" présent sur le menu de droite

Renseigner les valeurs suivantes :
	"Enable Mock SSL" : à cocher
	"Mock Port" : 8444 (par exemple, cela doit etre un port disponible)
	"Mock KeyStore" : Chemin vers le fichier data/keystore.jks
	"Mock Password" : "password" par defaut
	"Mock Key Password" : "password" par defaut
	"Mock TustStore" : Chemin vers le fichier data/trustore.jks
	"Mock TrustStore Password" : "password" par defaut

	
	PS : La clé publique doit être ajouter dans le trustore de la JVM sinon une erreur sera logger.
	
Récupération de la clé publique :
Prérequis : Démarrer le mock avec la config SSL ci-dessus
		
		openssl s_client -connect localhost:8444

Sauvegarder la clé dans un fichier (ex: soapui.pub)

Exemple de clé à sauvegarder :

-----BEGIN CERTIFICATE-----
MIIC3DCCAkWgAwIBAgICBNIwDQYJKoZIhvcNAQEFBQAwWDEOMAwGA1UEAxMFVGhl
Q0ExGzAZBgNVBAsTEk5PVCBGT1IgUFJPRFVDVElPTjEPMA0GA1UEChMGQXBhY2hl
MQswCQYDVQQIEwJOWTELMAkGA1UEBhMCVVMwHhcNMDkwNjIyMTUzNzA4WhcNMjkw
NjE3MTUzNzA4WjBYMQ4wDAYDVQQDEwVUaGVDQTEbMBkGA1UECxMSTk9UIEZPUiBQ
Uk9EVUNUSU9OMQ8wDQYDVQQKEwZBcGFjaGUxCzAJBgNVBAgTAk5ZMQswCQYDVQQG
EwJVUzCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA3st0/lxVtgRQMbMWvuQz
MHFv64qXPIm6sTvK3ff6ILGiOnx6wd98oQoJ+X1MuSVBv0ncf7qwZkxZ+imaf1zl
MwOw6Rha8yl0Uw2YbVYWFCldSXK/9+0KRTm3El5uzzGKT2dKJ2840Wh7LuYKGoEw
95xXfWb13K1SL13Ewn8Er1cCAwEAAaOBtDCBsTAdBgNVHQ4EFgQUj0/bfQrFQIB6
6jnKNpqmcGUjy7cwgYEGA1UdIwR6MHiAFI9P230KxUCAeuo5yjaapnBlI8u3oVyk
WjBYMQ4wDAYDVQQDEwVUaGVDQTEbMBkGA1UECxMSTk9UIEZPUiBQUk9EVUNUSU9O
MQ8wDQYDVQQKEwZBcGFjaGUxCzAJBgNVBAgTAk5ZMQswCQYDVQQGEwJVU4ICBNIw
DAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOBgQBnLYUUrajPw+Ku9Jr8oDte
KEe+zGDcEEa0uI8ld0XqLjcVcX1SGGYh13ophJCqn3kBwcXtgH1oxj9/M4fWr6pL
ml4oJMzrYGRi1M2bMgzWFhX5lhTTy/WCDRW05K9LDvTv94yRJV07uk1ztbYgpiyn
zjX4WJceNv6/6wzWTT7+Ow==
-----END CERTIFICATE-----




Ajouter la clé dans le trustore de la JVM (à faire sur chaque environnement

// Se placer dans le dossier ou ce trouve le keystore de la JVM (/opt/jdk/current/jre/lib/security)

 sudo keytool -import -keystore cacerts -alias AdileDemo -file /home/adile/Desktop/soapui.pub 
			
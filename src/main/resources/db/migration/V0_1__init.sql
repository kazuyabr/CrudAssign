DROP TABLE IF EXISTS status;
DROP TABLE IF EXISTS assinatura;
DROP TABLE IF EXISTS historico_assinatura;

CREATE TABLE status (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tipo VARCHAR(250) NOT NULL
);

CREATE TABLE assinatura (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  status_id INT NOT NULL,
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
  atualizado_em TIMESTAMP DEFAULT NULL
);

CREATE TABLE historico_assinatura (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  tipo VARCHAR(250) NOT NULL,
  id_assinatura INT NOT NULL,
  criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP()
);

ALTER TABLE assinatura
    ADD FOREIGN KEY (status_id) 
    REFERENCES status(id);
   
ALTER TABLE historico_assinatura
    ADD FOREIGN KEY (id_assinatura) 
    REFERENCES assinatura(id);

  
INSERT INTO status (tipo) VALUES
  ('ATIVA'),
  ('CANCELADA');
 
 INSERT INTO assinatura (status_id, criado_em, atualizado_em) VALUES
  (1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP()),
  (2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
  

INSERT INTO historico_assinatura (tipo, id_assinatura, criado_em) VALUES
  ('Digital', 1, CURRENT_TIMESTAMP()),
  ('Fisica', 1, CURRENT_TIMESTAMP());
 
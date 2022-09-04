INSERT INTO category (subcategory, category)
VALUES ('accessories', 'computers'),('monoblocks', 'computers'),
       ('pc', 'computers'),('pc peripherals', 'computers'),
       ('monitors', 'computers'),('nettops', 'computers'),
       ('mini pc', 'computers'),('network equipment', 'computers'),
       ('microcomputers', 'computers'),('industrial computers', 'computers'),
       ('servers', 'computers'),('software on media', 'computers'),
       ('mining', 'computers'),('laptops', 'computers'),
       ('gaming laptops', 'computers'),
       ('smartphones', 'telephones'),('SIM-cards', 'telephones'),
       ('accessories for smartphones', 'telephones'),('wired phones', 'telephones'),
       ('DECT-phones', 'telephones')
        ON DUPLICATE KEY UPDATE subcategory=VALUES(subcategory),
        category=VALUES(category);
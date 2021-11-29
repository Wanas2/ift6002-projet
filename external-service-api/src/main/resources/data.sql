INSERT INTO dino(id, species, food_type) VALUES(1, 'Ankylosaurus', 'Herbivore');
INSERT INTO dino(id, species, food_type) VALUES(2, 'Brachiosaurus', 'Herbivore');
INSERT INTO dino(id, species, food_type) VALUES(3, 'Diplodocus', 'Herbivore');
INSERT INTO dino(id, species, food_type) VALUES(4, 'Stegosaurus', 'Herbivore');
INSERT INTO dino(id, species, food_type) VALUES(5, 'Triceratops', 'Herbivore');

INSERT INTO dino(id, species, food_type) VALUES(6, 'Allosaurus', 'Carnivore');
INSERT INTO dino(id, species, food_type) VALUES(7, 'Megalosaurus', 'Carnivore');
INSERT INTO dino(id, species, food_type) VALUES(8, 'Spinosaurus', 'Carnivore');
INSERT INTO dino(id, species, food_type) VALUES(9, 'Tyrannosaurus Rex', 'Carnivore');
INSERT INTO dino(id, species, food_type) VALUES(10, 'Velociraptor', 'Carnivore');

INSERT INTO dino(id, species, food_type) VALUES(11, 'Eoraptor', 'Omnivore');
INSERT INTO dino(id, species, food_type) VALUES(12, 'Gigantoraptor', 'Omnivore');
INSERT INTO dino(id, species, food_type) VALUES(13, 'Heterodontosaurus', 'Omnivore');
INSERT INTO dino(id, species, food_type) VALUES(14, 'Ornithomimus', 'Omnivore');
INSERT INTO dino(id, species, food_type) VALUES(15, 'Struthiomimus', 'Omnivore');


INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(1, 1, 1, 'Ankylosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(2, 1, 2, 'Stegosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(3, 1, 3, 'Diplodocus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(4, 1, 4, 'Ankylosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(5, 1, 5, 'Triceratops', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(6, 2, 1, 'Allosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(7, 2, 2, 'Velociraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(8, 2, 3, 'Spinosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(9, 2, 4, 'Heterodontosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(10, 2, 5, 'Diplodocus', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(11, 3, 1, 'Heterodontosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(12, 3, 2, 'Triceratops', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(13, 3, 3, 'Ankylosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(14, 3, 4, 'Allosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(15, 3, 5, 'Triceratops', 'm');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(16, 4, 1, 'Diplodocus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(17, 4, 2, 'Allosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(18, 4, 3, 'Megalosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(19, 4, 4, 'Ankylosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(20, 4, 5, 'Spinosaurus', 'm');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(21, 5, 1, 'Diplodocus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(22, 5, 2, 'Triceratops', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(23, 5, 3, 'Velociraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(24, 5, 4, 'yrannosaurus Rex', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(25, 5, 5, 'Triceratops', 'm');


INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(26, 6, 6, 'Velociraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(27, 6, 7, 'Spinosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(28, 6, 8, 'Megalosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(29, 6, 9, 'Struthiomimus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(30, 6, 10, 'Ankylosaurus', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(31, 7, 6, 'Velociraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(32, 7, 7, 'Ornithomimus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(33, 7, 8, 'Megalosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(34, 7, 9, 'yrannosaurus Rex', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(35, 7, 10, 'Struthiomimus', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(36, 8, 6, 'Ornithomimus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(37, 8, 7, 'yrannosaurus Rex', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(38, 8, 8, 'Eoraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(39, 8, 9, 'Spinosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(40, 8, 10, 'Velociraptor', 'm');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(41, 9, 6, 'Spinosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(42, 9, 7, 'Velociraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(43, 9, 8, 'yrannosaurus Rex', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(44, 9, 9, 'Megalosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(45, 9, 10, 'Spinosaurus', 'm');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(46, 10, 6, 'Spinosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(47, 10, 7, 'Velociraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(48, 10, 8, 'Gigantoraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(49, 10, 9, 'yrannosaurus Rex', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(50, 10, 10, 'Megalosaurus', 'f');



INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(51, 11, 11, 'Gigantoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(52, 11, 12, 'Eoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(53, 11, 13, 'Gigantoraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(54, 11, 14, 'yrannosaurus Rex', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(55, 11, 15, 'Ornithomimus', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(56, 12, 11, 'Ornithomimus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(57, 12, 12, 'Eoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(58, 12, 13, 'Struthiomimus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(59, 12, 14, 'Megalosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(60, 12, 15, 'Heterodontosaurus', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(61, 13, 11, 'Eoraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(62, 13, 12, 'Heterodontosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(63, 13, 13, 'Eoraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(64, 13, 14, 'Heterodontosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(65, 13, 15, 'Ornithomimus', 'm');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(66, 14, 11, 'Velociraptor', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(67, 14, 12, 'Struthiomimus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(68, 14, 13, 'Gigantoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(69, 14, 14, 'Struthiomimus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(70, 14, 15, 'Eoraptor', 'f');

INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(71, 15, 11, 'Spinosaurus', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(72, 15, 12, 'Eoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(73, 15, 13, 'Heterodontosaurus', 'f');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(74, 15, 14, 'Gigantoraptor', 'm');
INSERT INTO breeding(id, mother_id, father_id, offspring, gender) VALUES(75, 15, 15, 'Struthiomimus', 'm');

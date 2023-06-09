INSERT INTO basic.public.national_cuisine(cuisine)
VALUES ('Italian'),
       ('Mexican'),
       ('Japanese'),
       ('Indian'),
       ('French'),
       ('Thai'),
       ('Chinese'),
       ('Korean'),
       ('Mediterranean'),
       ('Greek'),
       ('Spanish'),
       ('Lebanese'),
       ('Turkish'),
       ('Russian'),
       ('Brazilian'),
       ('Vietnamese'),
       ('Moroccan'),
       ('Peruvian'),
       ('Jamaican'),
       ('German'),
       ('Swiss');

INSERT INTO basic.public.dish (name, description)
VALUES ('Борщ', 'Суп из свёклы, с картофелем и мясом.'),
       ('Оливье', 'Салат из отварного картофеля, моркови, горошка, соленых огурцов, зеленого горошка, яиц и майонеза.'),
       ('Шашлык', 'Блюдо из кусков мяса на шпажках, обжаренных на открытом огне.'),
       ('Плов', 'Блюдо из риса и мяса или рыбы с приправами.'),
       ('Блины', 'Тонкие оладьи, готовимые на сковороде.'),
       ('Гречка', 'Зерновой продукт, получаемый из гречихи.'),
       ('Суп-харчо', 'Острый грузинский суп с мясом, рисом, томатной пастой и специями.'),
       ('Лазанья', 'Итальянское блюдо, представляющее собой запеканку из листьев пасты и мясного фарша.'),
       ('Котлеты по-киевски', 'Котлеты, в которых внутри находится масло с чесноком.'),
       ('Овощной рагу', 'Блюдо из овощей, тушенных в собственном соку.'),
       ('Хачапури', 'Грузинское блюдо, представляющее собой хлеб с начинкой из сыра.'),
       ('Гуляш', 'Венгерское блюдо из мяса и овощей, тушеных в красном вине.'),
       ('Цезарь', 'Салат из листов салата, кусочков куриного мяса, сыра пармезан, гренок и соуса цезарь.'),
       ('Рататуй', 'Французское блюдо, представляющее собой тушеные овощи.'),
       ('Салат "Витаминный"', 'Салат из свежих овощей.'),
       ('Карбонара', 'Итальянское блюдо из спагетти, яиц, сыра, гуанчалы и перца.'),
       ('Жаркое', 'Русское блюдо, представляющее собой тушеное мясо с овощами.'),
       ('Суп-пюре', 'Суп, измельченный в блендере до состояния пюре.');

INSERT INTO basic.public.ingredients (name, description)
VALUES ('Курица', 'Мясо птицы, которое может использоваться в различных блюдах'),
       ('Говядина', 'Мясо крупного рогатого скота'),
       ('Свинина', 'Мясо свиньи, часто используется в мясных блюдах'),
       ('Баранина', 'Мясо барана, используется в традиционных блюдах разных кухонь'),
       ('Картофель', 'Корнеплод, используется в качестве гарнира и ингредиента для различных блюд'),
       ('Морковь', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Лук', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Чеснок', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Перец', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Помидор', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Огурец', 'Овощ, используется в качестве ингредиента для различных блюд'),
       ('Шампиньоны', 'Грибы, используются в качестве ингредиента для различных блюд'),
       ('Рис', 'Зерновой продукт, используется в качестве гарнира и ингредиента для различных блюд'),
       ('Макароны', 'Мукальные изделия, используются в качестве гарнира и ингредиента для различных блюд'),
       ('Сыр', 'Молочный продукт, используется в качестве ингредиента для различных блюд'),
       ('Масло сливочное', 'Молочный продукт, используется в качестве ингредиента для различных блюд'),
       ('Масло растительное', 'Растительный продукт, используется в качестве ингредиента для различных блюд'),
       ('Молоко', 'Молочный продукт, используется в качестве ингредиента для различных блюд'),
       ('Гречка', 'Гречневая крупа, богатая белком и витаминами группы В'),
       ('Рис', 'Рисовая крупа, источник углеводов и клетчатки'),
       ('Овсянка', 'Овсяная крупа, содержит белок, клетчатку, витамины и минералы'),
       ('Пшено', 'Пшеничная крупа, богатая клетчаткой и белком'),
       ('Ячневая крупа', 'Изготавливается из ячменя, содержит много клетчатки и минералов'),
       ('Перловая крупа', 'Содержит много белка и углеводов, а также витамины группы В'),
       ('Манная крупа', 'Высококалорийный продукт, содержащий углеводы и витамины группы В'),
       ('Яблоко', 'Содержит большое количество пищевых волокон и полезных веществ'),
       ('Груша', 'Источник витаминов и минералов, богата калием и клетчаткой'),
       ('Апельсин', 'Содержит много витамина С, который укрепляет иммунитет и здоровье'),
       ('Мандарин', 'Содержит большое количество антиоксидантов и витаминов'),
       ('Лимон', 'Источник витаминов, помогает бороться с бактериями и вирусами'),
       ('Ананас', 'Содержит много ферментов, ускоряющих обмен веществ и помогающих снизить вес'),
       ('Банан', 'Богатый источник калия и магния, необходимых для нормальной работы сердца и нервной системы');

INSERT INTO basic.public.tastes (taste)
VALUES ('Сладкий'),
       ('Соленый'),
       ('Кислый'),
       ('Горький'),
       ('Острый'),
       ('Пряный'),
       ('Кисло-сладкий'),
       ('Сладко-соленый'),
       ('Нейтральный');

INSERT INTO basic.public.roles(name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');

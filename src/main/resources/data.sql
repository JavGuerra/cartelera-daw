-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `address`
--

INSERT INTO `address` (`id`, `map`, `street`, `city`, `country`, `postal_code`) VALUES
(1, '', 'Av. de Elvas, s/n.', 'Badajoz', 'España', '06006'),
(2, '', 'Avda. Luis De Morales, 3', 'Sevilla', 'España', '41005'),
(3, '', 'Centro Comercial Bahía Sur Avenida Caño, C. de la Herrera, s/n', 'Cádiz', 'España', '11100'),
(4, '', 'C.C. Vialia, C. Explanada de la Estación, s/n', 'Málaga', 'España', '29002'),
(5, '', 'Calle Leonardo Da Vinci, 17', 'Don Benito', 'España', '06400'),
(6, '', 'El Faro del Guadiana Shopping Centre, Av. de Elvas, s/n', 'Badajoz', 'España', '06006');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `users`
-- Contraseña: 632541
--

INSERT INTO `users` (`id`, `image`, `email`, `name`, `password`, `surname`, `username`) VALUES
(1, '', 'admin@cartelera.es', 'Admin', '$2a$10$uuAkGrH14kAnfPe2d7ApcOrDM2tTjz7JhPxa5yGeuE34j65cT6xFq', 'User', 'admin');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `cinema`
--

INSERT INTO `cinema` (`id`, `active`, `image`, `cif`, `email`, `facebook`, `instagram`, `linked_in`, `name`, `phone`, `twitter`, `url`, `address_id`) VALUES
(1, b'1', '/img/cinema/01.jpg', 'B8691034G', 'mk2@cinesur.com', '', '', '', 'mk2 Cinesur Conquistadores', '', '', 'https://www.cinesur.com/es/cine-mk2-cinesur-conquistadores', 1),
(2, b'1', '/img/cinema/02.jpg', 'B8691034H', 'mk2@cinesur.com', '', '', '', 'mk2 Cinesur Nervión Plaza', '', '', 'https://www.cinesur.com/es/cine-mk2-cinesur-nervion-plaza', 2),
(3, b'1', '/img/cinema/03.jpg', 'B8272147E', 'atencionalcliente@yelmocines.es', '', '', '', 'Yelmo Premium Bahía Sur', '', '', 'https://yelmocines.es/cartelera/cadiz/premium-bahia-sur', 3),
(4, b'1', '/img/cinema/04.jpg', 'B8272147F', 'atencionalcliente@yelmocines.es', '', '', '', 'Yelmo Vialia Málaga', '', '', 'https://yelmocines.es/cartelera/malaga', 4),
(5, b'1', '/img/cinema/05.jpg', 'B0621334S', 'cinevictoria@hotmail.com', '', '', '', 'Victoria', '', '', 'https://www.cinesvictoria.com/carteleradonbenito.html', 5),
(6, b'1', '/img/cinema/06.jpg', 'B8272147G', 'atencionalcliente@yelmocines.es', '', '', '', 'Yelmo Premium El Faro', '', '', 'https://yelmocines.es/cartelera/badajoz/premium-el-faro', 6);



-- --------------------------------------------------------

ALTER TABLE `film` MODIFY COLUMN `synopsis` VARCHAR(1000);

--
-- Volcado de datos para la tabla `film`
--

INSERT INTO `film` (`id`, `active`, `actor`, `classification`, `company`, `country`, `director`, `duration`, `music`, `photography`, `poster`, `rating`, `synopsis`, `review`, `script`, `title`, `trailer`, `year`) VALUES
(1, b'1', "Marisa Abela, Ryan O'Doherty, Jack O'Connell, Lesley Manville", 3, 'Monumental Pictures, Studiocanal', 'Reino Unido', 'Sam Taylor-Johnson', 122, 'Nick Cave, Warren Ellis', 'Polly Morgan', '/img/poster/01.jpg', 3, 'La vida personal y profesional de la cantante y compositora Amy Winehouse, cuya vida se truncó en 2011, a los 27 años, debido al consumo y abuso de alcohol y drogas.', 'https://www.filmaffinity.com/es/reviews/1/159385.html', 'Matt Greenhalgh', 'Back to Black', 'https://www.filmaffinity.com/es/evideos.php?movie_id=159385&dmplayersource=share-send', 2024),
(2, b'1', 'Mark Wahlberg, Nathalie Emmanuel, Simu Liu, Michael Landes', 1, 'Lionsgate, Tucker Tooley Entertainment', 'Estados Unidos', 'Simon Cellan Jones', 106, 'Kevin Matley', 'Jacques Jouffret', '/img/poster/02.jpg', 3, 'Mikael Lindnord, capitán del equipo sueco de atletismo de aventura, tuvo un extraño encuentro con un perro callejero durante una carrera en la jungla ecuatoriana. Desde entonces el perro le siguió por el resto del recorrido', 'https://www.filmaffinity.com/es/reviews/1/439872.html', 'Michael Brandt, Mikael Lindnord', 'Arthur', 'https://www.filmaffinity.com/es/evideos.php?movie_id=439872', 2024),
(3, b'1', 'Russell Crowe, Ryan Simpkins, Sam Worthington, Chloe Bailey', 3, 'Miramax, Outerbanks Entertainment', 'Estados Unidos', 'Joshua John Miller', 93, 'Danny Bensi, Saunder Jurriaans', 'Simon Duggan', '/img/poster/03.jpg', 2, 'Anthony Miller es un actor con problemas que empieza a desmoronarse durante el rodaje de una película de terror sobrenatural. Su hija se pregunta si su padre está volviendo a sus adicciones del pasado o si hay algo más siniestro detrás', 'https://www.filmaffinity.com/es/reviews/1/238020.html', 'Joshua John Miller', 'El exorcismo de Georgetown', 'https://www.filmaffinity.com/es/evideos.php?movie_id=238020', 2024),
(4, b'1', '', 1, 'TOHO Animation, Production I.G, Shueisha, Sony Music', 'Japón', 'Susumu Mitsunaka', 85, 'Yuki Hayashi, Asami Tachibana', 'Yumiko Nakata', '/img/poster/04.jpg', 4, 'El encuentro entre los equipos rivales de Karasuno y Nekoma hará que la tensión aumente, ya que ambos equipos están decididos a salir victoriosos en el campeonato nacional de voleibol.', 'https://www.filmaffinity.com/es/reviews/1/323347.html','Susumu Mitsunaka. Manga: Haruichi Furudate', 'Haikyu!! La batalla del basurero', 'https://www.filmaffinity.com/es/evideos.php?movie_id=323347', 2024),
(5, b'1', 'Pio Marmai, Noémie Merlant, Jonathan Cohen, Mathieu Amalric', 2, 'Quad, Ten Cinéma, Gaumont, TF1 Films Production', 'Francia', 'Olivier Nakache, Eric Toledano', 120, '', 'Mélodie Preel', '/img/poster/05.jpg', 3, 'Albert y Bruno son compradores compulsivos, están en números rojos y endeudados. Conocen a un grupo de jóvenes activistas medioambientales y, más atraídos por la cerveza que por sus argumentos, se integrarán al movimiento sin convicción.', 'https://www.filmaffinity.com/es/reviews/1/924798.html','Olivier Nakache, Eric Toledano', 'Un año difícil', 'https://www.filmaffinity.com/es/evideos.php?movie_id=924798', 2023),
(6, b'1', 'Almudena Amor, Javier Rey, Amanda Goldsmith, Yanely Hernández', 3, 'La Claqueta PC, Coming Soon Films, RTVE', 'España', 'Laura Alvea', 120, 'Alfred Tapscott', 'Fran Fernández Pardo', '/img/poster/06.jpg', 2, 'Ana, auxiliar de enfermería, comienza a sentirse atraída por Agustín, el marido de una mujer en estado vegetativo a la que ella cuida. Entonces empieza a ser acosada por fenómenos paranormales que tratan de echarla de la casa y separarla de Agustín.', 'https://www.filmaffinity.com/es/reviews/1/117802.html','Miguel Ibáñez Monroy, Daniel González, Marta Armengol, Laura Alvea', 'La mujer dormida', 'https://www.filmaffinity.com/es/evideos.php?movie_id=117802', 2024),
(7, b'1', 'Sophie Nélisse, Dougray Scott, Andrzej Seweryn, Eliza Rycembel', 3, 'Darius Filmsc, Entract Studios, K&K Selekt, Téléfilm Canada', 'Canadá', 'Louise Archambault', 121, 'Maxime Navert, Alexandra Stréliski', 'Paul Sarossy', '/img/poster/07.jpg', 3, 'Cuando los nazis invaden Polonia en 1939, la enfermera Irena Gut es obligada a trabajar para el ejército alemán, siendo asignada como ama de llaves de un comandante nazi. Gut arriesgará todo para salvar a una docena de judíos', 'https://www.filmaffinity.com/es/reviews/1/807943.html','Dan Gordon', 'La promesa de Irene', 'https://www.filmaffinity.com/es/evideos.php?movie_id=807943', 2023),
(8, b'1', 'Jaime Ray Newman, Zar Amir-Ebrahimi, Nadine Marshall, Arienne Mandi', 2, ' Keshet Studios, Maven Pictures, New Native Pictures, Sarke Studio, WestEnd Films', 'Georgia', 'Zar Amir-Ebrahimi, Guy Nattiv', 105, 'Dascha Dauenhauer', 'Todd Martin', '/img/poster/08.jpg', 3, 'La judoka iraní Leila y su entrenadora Maryam viajan a un Campeonato con la intención de ganar medalla de oro para Irán, pero reciben un ultimátum de su país, exigiéndole fingir una lesión y perder. Leila se enfrenta a una decisión imposible', 'https://www.filmaffinity.com/es/reviews/1/375002.html','Elham Erfani, Guy Nattiv', 'Tatami', 'https://www.filmaffinity.com/es/evideos.php?movie_id=375002', 2023),
(9, b'1', 'Laure Calamy, Vincent Elbaz, Suzanne De Baecque, Sylvain Katan', 2, 'Chapka Films, La Filmerie, France 3 Cinéma', 'Francia', 'Caroline Vignal', 97, 'Benjamin Esdraffo', 'Martin Roux', '/img/poster/09.jpg', 2, 'Iris tiene un marido y dos hijas maravillosas, un consultorio con clientes, amigos que la entienden... Y pronto cumplirá 50. Y entonces un extraño planta un pensamiento: "Toma un amante" y los candidatos emergen como si llovieran hombres.', 'https://www.filmaffinity.com/es/reviews/1/240030.html','Caroline Vignal, Noémie de Lapparent', 'Iris', 'https://www.filmaffinity.com/es/evideos.php?movie_id=240030', 2024),
(10, b'1', '', 0, 'France 3 Cinéma, Master Films, SND Groupe M6', 'Francia', 'Laurent Bru, Yannick Moulin, Benoît Somville', 89, 'Olivier Cussac', '', '/img/poster/10.jpg', 3, 'Cuando un misterioso supervillano cubre la selva con una espuma rosa tóxica que explota al contacto con el agua, la Jungle Bunch es llamada al rescate. A menos de un mes de la estación de las lluvias, ¡empieza la carrera! Nuestros héroes, a los que se unirán nuevos aliados, viajarán por todo el mundo en busca de un antídoto. Desde las lejanas regiones del Norte, hasta Europa, Asia y Oriente Medio, se embarcarán en la mayor búsqueda de su historia. Esta vez, se enfrentan a un adversario más inteligente y diabólico que nunca. Para Maurice y sus amigos, ¡nunca ha habido tanto en juego', 'https://www.filmaffinity.com/es/reviews/1/766649.html','David Alaux, Eric Tosti, Jean-François Tosti', 'Pandilla al rescate', 'https://www.filmaffinity.com/es/evideos.php?movie_id=766649', 2023),
(11, b'1', 'Fernando Mier Espina, Manolo Mier Espina', 0, 'Wanda Films, Báltico', 'España', 'Samu Fuentes', 89, 'Paloma Peñarrubia', 'Alejo Ramos Sabugo', '/img/poster/11.jpg', 3, 'Desde hace más de 5000 años los pastores habitan estas montañas de Picos de Europa. Para los hermanos Mier, pastores desde los 13 años, éste sigue siendo su hogar. El año pasado tuvieron que vender su rebaño de ovejas. La climatología y el paso de las estaciones condiciona su actividad. Se mueven de la Majada de Tordin a las invernales de Vierru y viceversa. Tras los duros inviernos, la llegada de la primavera supone un cambio importante en su día a día. Después llegará nuevamente el verano, y tras él, irremediablemente otra vez el otoño, que dará paso a un nuevo invierno. En un lugar donde muchos no conseguirían sobrevivir ellos disfrutan la vida.', 'https://www.filmaffinity.com/es/reviews/1/988533.html','Samu Fuentes', 'Los últimos pastores', 'https://www.filmaffinity.com/es/evideos.php?movie_id=988533', 2023),
(12, b'1', 'Hamid Reza Abbasi, Sadaf Asgari, Keyvan Mohammadi, Mehrdad Bakhshi', 2, 'ARTE, Basis Berlin Filmproduktion, Das Kleine Fernsehspiel, Living Pictures Production', 'Alemania', 'Behrooz Karamizade', 101, 'Saba Alizadeh, John Gürtler, Jan Miserre', 'Ashkan Ashkani', '/img/poster/12.jpg', 3, 'DAmir y Narges, dos enamorados que podrían describirse como nacidos bajo una estrella de la mala suerte. El joven procede de una familia pobre y, tras ser despedido de su trabajo de camarero, se ve obligado a buscar trabajo en una comunidad pesquera. Narges tiene un estatus social más alto y procede de una "buena familia". Por el momento, mantienen su relación en secreto. Para poder casarse, Amir tiene que ganar suficiente dinero para la dote.', 'https://www.filmaffinity.com/es/reviews/1/188130.html','Behrooz Karamizade', 'Redes vacías', 'https://www.filmaffinity.com/es/evideos.php?movie_id=188130', 2023),
(13, b'1', '', 0, 'Sacrebleu Productions, Take Five, Ciel de Paris Productions.', 'Francia', 'Benoît Chieux', 74, 'Pablo Pico', '', '/img/poster/13.jpg', 4, 'Juliette y Carmen, de 4 y 8 años, son dos intrépidas hermanas que, durante un momento de aburrimiento, descubren un pasaje secreto hacia el mundo de su libro preferido, El Reino de los Vientos. Transformadas en gatas, las niñas son encarceladas y separadas una de la otra. Nuestras heroínas tendrán que hacer gala de coraje y audacia para reencontrarse y volver al mundo real. Con la ayuda de la cantante Selma, se enfrentarán a Sirocco, el terrible mago, Maestro de los vientos y las tormenta. ¿Será tan aterrador como sugieren los libros?', 'https://www.filmaffinity.com/es/film418843.html','Benoît Chieux, Alain Gagnol', 'Sirocco y el reino de los vientos', 'https://www.filmaffinity.com/es/evideos.php?movie_id=418843', 2023),
(14, b'1', 'Samuele Segreto, Gabriele Pizzurro, Simone Raffaele Cordiano, Antonio de Matteo', 3, 'Fenix Entertainment, Ibla Film, RAI Cinema', 'Italia', 'Beppe Fiorello', 134, 'Daniele Bonaviri, Leonardo Milani', 'Ramiro Civita', '/img/poster/14.jpg', 4, 'Sicilia, junio de 1982. Mientras los italianos sueñan con ganar la Copa del Mundo, dos adolescentes sueñan con vivir su historia de amor sin miedo.', 'https://www.filmaffinity.com/es/reviews/1/821833.html','Andrea Cedrola, Beppe Fiorello.', 'Fuegos artificiales', 'https://www.filmaffinity.com/es/evideos.php?movie_id=821833', 2023),
(15, b'1', 'Daniel Ibañez, Cristalino, Stéphanie Magnin, Mafo', 3, 'La Terraza Films, Áralan Films, BTeam Pictures, Ikiru Films, Sideral Cinema, Los Ilusos Films, Toxicosmos.', 'España', 'Isaki Lacuesta, Pol Rodríguez', 109, 'Ylia, Los Planetas', 'Takuro Takeuchi', '/img/poster/15.jpg', 4,'Granada, finales de los 90. En plena efervescencia artística y cultural, un grupo de música indie vive su momento más delicado: la bajista rompe con la banda buscando su sitio fuera de la música y el guitarrista está inmerso en una peligrosa espiral de autodestrucción. Mientras, el cantante se enfrenta a un complicado proceso de escritura y grabación de su tercer disco. Nadie sabe que ese disco cambiará para siempre la escena musical de todo el país. Esta (no) es una película sobre Los Planetas', 'https://www.filmaffinity.com/es/reviews/1/385824.html','Isaki Lacuesta, Fernando Navarro', 'Segundo premio', 'https://www.filmaffinity.com/es/evideos.php?movie_id=385824', 2024),
(16, b'1', 'Anya Taylor-Joy, Chris Hemsworth, Tom Burke, Angus Sampson', 3, 'Warner Bros., Kennedy Miller Mitchell.', 'Australia', 'George Miller', 148, 'Junkie XL', 'Simon Duggan', '/img/poster/16.jpg', 4, 'Granada, finales de los 90. Al caer el mundo, la joven Furiosa es arrebatada del "Lugar Verde de Muchas Madres" y cae en manos de una horda de motoristas liderada por el Señor de la Guerra, Dementus. Arrasando el Páramo, se topan con la Ciudadela, presidida por Inmortal Joe. Mientras los dos tiranos luchan por el dominio, Furiosa debe sobrevivir a muchas pruebas mientras reúne los medios para encontrar el camino de vuelta a casa. Precuela de Mad Max: Furia en la carretera', 'https://www.filmaffinity.com/es/reviews/1/501691.html','Nick Lathouris, George Miller', 'Furiosa: De la saga Mad Max', 'https://www.filmaffinity.com/es/evideos.php?movie_id=501691', 2024),
(17, b'1', 'Yvan Attal, Maïwenn, Guillaume Canet, Marie-Josée Croze', 3, 'Curiosa Films, Films Sous Influence, SND Films, France 2 Cinema, UMédia', 'Francia', 'Yvan Attal', 85, 'Dan Levy', 'Rémy Chevrin', '/img/poster/17.jpg', 2, 'Mathieu es un marido fiel que lleva una vida tranquila con su esposa, mientras encubre las aventuras amorosas de su amigo Vincent. Un día, mientras Mathieu intenta suavizar las cosas con la última amante de Vincent, inesperadamente se enamora de ella.', 'https://www.filmaffinity.com/es/reviews/1/612853.html','Yaël Langmann, Yvan Attal.', 'Jugando con fuego', 'https://www.filmaffinity.com/es/evideos.php?movie_id=612853', 2023),
(18, b'1', 'Jean François Cayrey, Julien Pestel, Chantal Ladesou, André Penvern', 1, 'Curiosa Films, Starman Films', 'Francia', 'Frédéric Forestier, Antonin Fourlon', 101, '', 'Christian Abomnes', '/img/poster/18.jpg', 3, 'Decididos a vivir una nueva vida más cerca de la naturaleza, una pareja parisina y sus dos hijos se mudan a una pequeña comunidad rural. Pero cuando descubren que todos sus nuevos vecinos son cazadores entusiastas, deben hacer todo lo posible para acabar con ellos.', 'https://www.filmaffinity.com/es/reviews/1/164108.html','Antonin Fourlon', 'Se abre la veda', 'https://www.filmaffinity.com/es/evideos.php?movie_id=164108', 2023),
(19, b'1', 'Voz: Rosalie Chiang, Sandra Oh, Ava Morse', 0, 'Pixar Animation Studios, Walt Disney Pictures', 'Estados Unidos', 'Domee Shi', 101, "Ludwig Göransson. Canciones: Billie Eilish, Finneas O'Connell", 'Mahyar Abousaeedi, Jonathan Pytko', '/img/poster/19.jpg', 4, 'Mei Lee, una niña de 13 años un poco rara pero segura de sí misma, se debate entre ser la hija obediente que su madre quiere que sea y el caos propio de la adolescencia. Ming, su protectora y ligeramente exigente madre, no se separa nunca de ella lo que es una situación poco deseable para una adolescente. Y por si los cambios en su vida y en su cuerpo no fueran suficientes, cada vez que se emociona demasiado (lo que le ocurre prácticamente todo el tiempo), se convierte en un panda rojo gigante.', 'https://www.filmaffinity.com/es/reviews/1/896906.html','Domee Shi, Julia Cho.', 'Red', 'https://www.filmaffinity.com/es/evideos.php?movie_id=896906', 2022),
(20, b'1', 'Juan Diego, Fernando Tejero, Maggie Civantos, Eduardo Blanco', 1, 'Guainot Produce, Mother Superior, Avant Events', 'España', 'Paco Sepúlveda', 90, 'Gabriel Casacuberta, German Hernandez', 'David Hebrero, Alberto Pareja, Javier Ventura', '/img/poster/20.jpg', 3, 'Once historias nos reflejan que todos somos más parecidos de lo que pensamos. No importa de dónde vengamos, quiénes seamos o dónde hayamos nacido, todos sentimos amor, miedo, felicidad, esperanza… y esos sentimientos nos unen.', 'https://www.filmaffinity.com/es/reviews/1/218437.html','Paco Sepúlveda', 'Historias', 'https://www.filmaffinity.com/es/evideos.php?movie_id=218437', 2024),
(21, b'1', 'David Dastmalchian, Laura Gordon, Ian Bliss, Fayssal Bazzi', 3, 'Future Pictures, Image Nation, Spooky Pictures.', 'Australia', 'Cameron Cairnes, Colin Cairnes', 9, 'Glenn Richards', 'Matthew Temple', '/img/poster/21.jpg', 4, 'Una transmisión de televisión en directo en 1977 sale terriblemente mal, desatando el mal en las casas de todo el país.', 'https://www.filmaffinity.com/es/reviews/1/897658.html','Cameron Cairnes, Colin Cairnes', 'El último late night', 'https://www.filmaffinity.com/es/evideos.php?movie_id=897658', 2023),
(22, b'1', 'Iryna Kudashova, Pavlo Li, Tetiana Malkova, Tomasz Sobczak, Kostyantyn Temlyak, Irma Vitovskaya, Rimma Zyubina', 1, 'Future Pictures, Image Nation, Spooky Pictures.','Ucrania', 'Alexander Berezan', 117, 'Nadiia Odesuk', 'Yevgeny Usanov', '/img/poster/22.jpg', 3, 'Una joven cocinera huye para perseguir su sueño de convertirse en chef de un prestigioso restaurante de la ciudad. Pero cuando encuentra un libro de cocina de 1929, escrito por la legendaria chef Olga Franko, su vida da un giro inesperado.', 'https://www.filmaffinity.com/es/film790882.html','Catherine Breillat', 'Sabor a libertad', 'https://www.filmaffinity.com/es/evideos.php?movie_id=790882', 2023),
(23, b'1', 'Léa Drucker, Samuel Kircher, Olivier Rabourdin, Clotilde Courau', 3, 'SBS Productions, Cine+, Pyramide Productions, Canal+', 'Francia', 'Catherine Breillat', 104, '', 'Jeanne Lapoirie', '/img/poster/23.jpg', 3, 'Anne, una brillante abogada que vive con su esposo Pierre y sus hijas, entabla gradualmente una relación apasionada con Theo, el hijo de Pierre de un matrimonio anterior, poniendo en peligro su carrera y su vida familiar.', 'https://www.filmaffinity.com/es/reviews/1/132707.html','Catherine Breillat', 'El último verano', 'https://www.filmaffinity.com/es/evideos.php?movie_id=132707', 2023),
(24, b'1', 'Slawomir Grzymkowski, Adam Ferency, Marcin Tronski, Katarzyna Zawadzka', 3, 'Fundacja Filmowa im. Sw. Maksymiliana Kolbe, TVP S.A Telewizja Polska, Centrum Technologii Audiowizualnych, Polski Instytut Sztuki Filmowej', 'Polonia', 'Michał Kondrat', 140, 'Bartosz Chajdecki', 'Mateusz Pastewka', '/img/poster/24.jpg', 3, 'La historia del cardenal Stefan Wyszynski sienta las bases para el dramático ascenso del Papa Juan Pablo II y la caída del comunismo en Europa. ¿Quién es este hombre profético que luchó contra el mal y vio ascender a un hijo de Polonia?', 'https://www.filmaffinity.com/es/reviews/1/469006.html','Michał Kondrat', 'El primado de Polonia', 'https://www.filmaffinity.com/es/evideos.php?movie_id=469006', 2022),
(25, b'1', 'Cailey Fleming, Ryan Reynolds, John Krasinski, Fiona Shaw', 0, 'Paramount Pictures, Sunday Night, Maximum Effort, Platinum Dunes.', 'Estados Unidos', 'John Krasinski', 104, 'Michael Giacchino', 'Janusz Kaminski','/img/poster/25.jpg', 3, 'Una niña pasa por una experiencia difícil y entonces empieza a ver a los amigos imaginarios de todo el mundo que se han ido quedando atrás a medida que sus amigos de la vida real han ido creciendo.', 'https://www.filmaffinity.com/es/reviews/1/534650.html','John Krasinski', 'Amigos imaginarios', 'https://www.filmaffinity.com/es/evideos.php?movie_id=534650', 2024),
(26, b'1', 'Jaime Lorente, Alberto Ammann, Blanca Suárez, Alejandro Speitzer', 3, 'Nadie es perfecto, La Chica de la Curva, SDB Films, Atresmedia Televisión, Netflix, TV3', 'España', 'Kike Maíllo', 104, 'Camilla Uboldi', 'Marc Miró','/img/poster/26.jpg', 3, 'En la España de mediados de los 80, un grupo de amigos capitaneados por Xavi Font, acaban de llegar a Ibiza persiguiendo su sueño de dedicarse a la moda. Allí les descubre el productor José Luís Gil, un magnate de la industria musical que busca nuevos talentos para lanzar un grupo. Sin tener ni idea de cantar, el grupo inicia una carrera que les lleva de vivir como hippies en Ibiza, a llenar estadios en Latinoamérica y revolucionar de paso las discotecas de medio mundo. Crearon un nuevo estilo, fueron perseguidos por fans y ganaron millones, aunque para ello tuvieron que sacrificar su libertad.', 'https://www.filmaffinity.com/es/reviews/1/761189.html','Marta Libertad, Kike Maíllo', 'Disco Ibiza Locomía', 'https://www.filmaffinity.com/es/evideos.php?movie_id=761189', 2024),
(27, b'1', 'Belén Rueda, Ilay Kurelovic, Irene Escolar, Manuela Vellés', 2, 'Ejercicios de Equilibrio, Corte y Confección de Películas, Suspense Entertainment, Marrowbone.', 'España', 'Laura Jou', 90, 'Clara Peya', 'Marc Gómez del Moral','/img/poster/27.jpg', 2, 'Marisol es la entrenadora nacional de gimnasia rítmica, metódica, exigente y autoritaria. Se acerca el Campeonato del Mundo y ella deposita todas sus esperanzas en la medalla de oro en Angélica, la recién llegada más prometedora del equipo. Dos semanas antes de la final, Marisol descubre que su marido tiene una aventura con una mujer mucho más joven con la que espera un hijo. Marisol no contempla el fracaso, por lo que emprende una carrera desesperada por reconquistarlo, sin tener en cuenta sus motivos y sentimientos. Su dolor se traslada al tatami, donde se vuelve aún más implacable con Angélica, en quien deposita sus esperanzas de éxito. En este punto, ganar el oro es todo lo que le queda.', 'https://www.filmaffinity.com/es/reviews/1/285410.html','Bernat Vilaplana', 'Caída libre', 'https://www.filmaffinity.com/es/evideos.php?movie_id=285410', 2024),
(28, b'1', 'Meg Ryan, David Duchovny', 1, 'Ten Acre Films, Rockhill Studios, Das Films, Prowess Pictures.', 'Estados Unidos', 'Meg Ryan', 105, 'David Boman','Bartosz Nalazek', '/img/poster/28.jpg', 2,'Los antiguos amantes Willa y Bill se reencuentran en un aeropuerto por primera vez desde que se separaron décadas antes. Atrapados por la nieve, lo único que quieren es llegar a casa lo antes posible. Sin embargo, en el transcurso de una noche, gradualmente se sienten de nuevo atraídos, obligados a revisar lo que podría haber sido su relación y lo que bien podría ser nuevamente.', 'https://www.filmaffinity.com/es/reviews/1/958573.html','Steven Dietz, Kirk Lynn, Meg Ryan.', 'Lo que sucede después', 'https://www.filmaffinity.com/es/evideos.php?movie_id=958573', 2023),
(29, b'1', 'Vincent Lacoste, François Cluzet, Adèle Exarchopoulos, William Lebghil', 1, 'Les Films du Parc, 31 Juin Films, France 2 Cinema, Les Films de Benjamin.', 'Francia', 'Thomas Lilti', 101, '', 'Antoine Heberlé','/img/poster/29.jpg', 4,'Empieza un nuevo curso escolar. Benjamin es un estudiante de doctorado sin beca. Ante la falta de perspectivas de futuro, acepta un trabajo como profesor en un instituto de París. Sin formación ni experiencia descubre lo duro que puede ser la profesión de maestro en un sistema educativo afectado por una falta de recursos crónica. Con el apoyo y el compromiso del resto de docentes, y un poco de suerte, se replanteará su vocación.', 'https://www.filmaffinity.com/es/reviews/1/554648.html','Thomas Lilti', 'Los buenos profesores', 'https://www.filmaffinity.com/es/evideos.php?movie_id=554648', 2023),
(30, b'1', 'Selma Alaoui, Veerle Baetens, Guillaume Duhesme, Anne Dorval', 2, 'Versus Production, Colonelle Films, Haut et Court', 'Bélgica', 'Delphine Girard', 108, 'Ben Shemie', 'Juliette Van Dormael','/img/poster/30.jpg', 3,'La fiesta se tuerce: Dary agrede a Aly. Cuando Aly llama a la policía, es Anna quien responde y consigue evitar que la agresión vaya a más. Al día siguiente, Aly intenta seguir con su vida como si nada y relativiza los efectos de la agresión. Dary trata de autoconvencerse de una versión de los hechos que no le quita el sueño. Anna hace por seguir involucrada en el asunto. Comienza el juicio. ¿Qué consigue la justicia? ¿Qué se puede reparar?', 'https://www.filmaffinity.com/es/reviews/1/590938.html','Delphine Girard', 'Víctima imperfecta', 'https://www.filmaffinity.com/es/evideos.php?movie_id=590938', 2023),
(31, b'1', 'Paula Grimaldo, Ariadna Gil, Pol Hermoso, Luis Bermejo.', 3, 'Calladita Films, Potenza Producciones, Decentralized Pictures', 'España', 'Miguel Faus', 92, 'Paula Olaz', 'Antonio Galisteo','/img/poster/31.jpg', 4,'Ana, recién llegada de Colombia, es empleada doméstica en una lujosa mansión donde veranea una adinerada familia de marchantes de arte. La joven trabaja de sol a sol, sin contrato, bajo la promesa de conseguir condiciones dignas al final del verano, siempre y cuando sea discreta y calladita. Pero a través de Gisela, la empleada de la casa vecina, Ana descubrirá que las cosas no funcionan exactamente como le han contado, y aprenderá a divertirse un poco más durante su verano en la Costa Brava.', 'https://www.filmaffinity.com/es/reviews/1/166600.html','Miguel Faus', 'Calladita', 'https://www.filmaffinity.com/es/evideos.php?movie_id=166600', 2023),
(32, b'1', 'Laia Artigas, Nuria Prims, Nunu Sales, Jaume Vilalta, Mercé Pons.', 2, 'Atiende Films', 'España', 'Mònica Cambra, Ariadna Fortuny', 79, 'Guillermo Martorell', 'Àssia J. La-Roca','/img/poster/32.jpg', 3,'Mila (Laia Artigas), su hermana mayor (Nunu Sales) y su madre (Núria Prims), pasan unos días en una casa de campo, aisladas del mundanal ruido. Sin embargo, se respira en el ambiente una calma cada vez más tensa, y los nervios están a flor de piel. Las incómodas miradas, los silencios y las ausencias revelan la inminencia de un hecho irreversible, y para exorcizar todo eso Mila decide organizar una fiesta que se irá convirtiendo, a medida que se acerca, en mucho más que una simple celebración.', 'https://www.filmaffinity.com/es/reviews/1/458270.html','Ariadna Fortuny, Clàudia Garcia de Dios', 'Un sol radiant', 'https://www.filmaffinity.com/es/evideos.php?movie_id=458270', 2023);
-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `film_genres`
--

INSERT INTO `film_genres` (`film_id`, `genres`) VALUES
(1, 1),
(2, 1),
(3, 6),
(4, 0),
(5, 0),
(6, 1),
(7, 10),
(8, 7),
(9, 0),
(10, 9),
(11, 12),
(12, 1),
(13, 4),
(14, 1),
(15, 3),
(16, 1),
(17, 7),
(18, 0),
(19, 4),
(20, 1),
(21, 6),
(22, 1),
(23, 1),
(24, 10),
(25, 9),
(26, 5),
(27, 7),
(28, 8),
(29, 1),
(30, 1),
(31, 1),
(32, 1);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id`, `active`, `capacity`, `premiere`, `room_number`, `cinema_id`, `film_id`) VALUES
(null, b'1', 130, '2024-05-10', 1, 1, 1),
(null, b'1', 130, '2024-05-11', 2, 1, 2),
(null, b'1', 130, '2024-05-12', 3, 1, 3),
(null, b'1', 130, '2024-05-13', 4, 1, 4),
(null, b'1', 130, '2024-05-14', 5, 1, 5),

(null, b'1', 130, '2024-05-15', 1, 2, 6),
(null, b'1', 130, '2024-05-16', 2, 2, 7),
(null, b'1', 130, '2024-05-17', 3, 2, 8),
(null, b'1', 130, '2024-05-18', 4, 2, 9),
(null, b'1', 130, '2024-05-19', 5, 2, 10),

(null, b'1', 130, '2024-05-20', 1, 3, 11),
(null, b'1', 130, '2024-05-21', 2, 3, 12),
(null, b'1', 130, '2024-05-22', 3, 3, 13),
(null, b'1', 130, '2024-05-23', 4, 3, 14),
(null, b'1', 130, '2024-05-24', 5, 3, 15),

(null, b'1', 130, '2024-05-25', 1, 4, 16),
(null, b'1', 130, '2024-05-26', 2, 4, 17),
(null, b'1', 130, '2024-05-27', 3, 4, 18),
(null, b'1', 130, '2024-05-28', 4, 4, 19),
(null, b'1', 130, '2024-05-29', 5, 4, 20),

(null, b'1', 130, '2024-05-25', 1, 5, 1),
(null, b'1', 130, '2024-05-26', 2, 5, 2),
(null, b'1', 130, '2024-06-01', 3, 5, 3),
(null, b'1', 130, '2024-06-02', 4, 5, 4),
(null, b'1', 130, '2024-06-03', 5, 5, 5),

(null, b'1', 130, '2024-05-25', 1, 6, 21),
(null, b'1', 130, '2024-05-26', 2, 6, 22),
(null, b'1', 130, '2024-06-01', 3, 6, 23),
(null, b'1', 130, '2024-06-02', 4, 6, 24),
(null, b'1', 130, '2024-06-03', 5, 6, 25);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `room_schedules`
--

INSERT INTO `room_schedules` (`room_id`, `schedules`) VALUES
(1, '16:00'),
(2, '16:00'),
(3, '16:00'),
(4, '16:00'),
(5, '16:00'),
(6, '16:00'),
(7, '16:00'),
(8, '16:00'),
(9, '16:00'),
(10, '16:00'),
(11, '16:00'),
(12, '16:00'),
(13, '16:00'),
(14, '16:00'),
(15, '16:00'),
(16, '16:00'),
(17, '16:00'),
(18, '16:00'),
(19, '16:00'),
(20, '16:00'),
(21, '16:00'),
(22, '16:00'),
(23, '16:00'),
(24, '16:00'),
(25, '16:00'),
(26, '16:00'),
(27, '16:00'),
(28, '16:00'),
(29, '16:00'),
(30, '16:00'),
(1, '18:00'),
(2, '18:00'),
(3, '18:00'),
(4, '18:00'),
(5, '18:00'),
(6, '18:00'),
(7, '18:00'),
(8, '18:00'),
(9, '18:00'),
(10, '18:00'),
(11, '18:00'),
(12, '18:00'),
(13, '18:00'),
(14, '18:00'),
(15, '18:00'),
(16, '18:00'),
(17, '18:00'),
(18, '18:00'),
(19, '18:00'),
(20, '18:00'),
(21, '18:00'),
(22, '18:00'),
(23, '18:00'),
(24, '18:00'),
(25, '18:00'),
(26, '18:00'),
(27, '18:00'),
(28, '18:00'),
(29, '18:00'),
(30, '18:00'),
(1, '20:00'),
(2, '20:00'),
(3, '20:00'),
(4, '20:00'),
(5, '20:00'),
(6, '20:00'),
(7, '20:00'),
(8, '20:00'),
(9, '20:00'),
(10, '20:00'),
(11, '20:00'),
(12, '20:00'),
(13, '20:00'),
(14, '20:00'),
(15, '20:00'),
(16, '20:00'),
(17, '20:00'),
(18, '20:00'),
(19, '20:00'),
(20, '20:00'),
(21, '20:00'),
(22, '20:00'),
(23, '20:00'),
(24, '20:00'),
(25, '20:00'),
(26, '20:00'),
(27, '20:00'),
(28, '20:00'),
(29, '20:00'),
(30, '20:00'),
(1, '22:00'),
(2, '22:00'),
(3, '22:00'),
(4, '22:00'),
(5, '22:00'),
(6, '22:00'),
(7, '22:00'),
(8, '22:00'),
(9, '22:00'),
(10, '22:00'),
(11, '22:00'),
(12, '22:00'),
(13, '22:00'),
(14, '22:00'),
(15, '22:00'),
(16, '22:00'),
(17, '22:00'),
(18, '22:00'),
(19, '22:00'),
(20, '22:00'),
(21, '22:00'),
(22, '22:00'),
(23, '22:00'),
(24, '22:00'),
(25, '22:00'),
(26, '22:00'),
(27, '22:00'),
(28, '22:00'),
(29, '22:00'),
(30, '22:00');

-- --------------------------------------------------------

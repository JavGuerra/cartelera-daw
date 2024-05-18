-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `address`
--

INSERT INTO `address` (`id`, `image`, `street`, `city`, `country`, `postal_code`) VALUES
(1, 'https://cualbondi.com.ar/media/poi.webp/poi-cines-aana-san-juan.880x300.webp', 'C/ Callejón, 2', 'Alicante', 'España', '23654'),
(2, 'https://www.pillalas.com/static/_s/img/cines/mapa_349566086E5F21EF.jpg', 'C/ Calle Cita, 5', 'Madrid', 'España', '41258'),
(3, 'https://cualbondi.com.ar/media/poi/poi-cines-golem-baiona.500x500.png', 'C/ Carrerón, 3', 'Bayona', 'España', '96587'),
(4, 'https://www.alimarket.es/media/images/mapas/censos/2/image-map-1049382-640x416-z17-scale2.png', 'C/ Carrerita, 1', 'Badajoz', 'España', '12365'),
(5, 'https://www.alimarket.es/media/images/mapas/censos/6/image-map-1064966-640x416-z17-scale2.png', 'C/ Camino, 2', 'Abrera', 'España', '12123');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `app_users`
-- Contraseña: 632541
--

INSERT INTO `users` (`id`, `image`, `email`, `name`, `password`, `surname`, `username`) VALUES
(1, 'https://xsgames.co/randomusers/assets/avatars/female/14.jpg', 'doloresem@il', 'Dolores', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Fuertes', 'dfuertes'),
(2, 'https://xsgames.co/randomusers/assets/avatars/male/27.jpg', 'manuelem@il', 'Manuel', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Perez', 'mperez'),
(3, 'https://xsgames.co/randomusers/assets/avatars/female/19.jpg', 'rosaem@il', 'Rosa', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Fuentes', 'rfuentes'),
(4, 'https://xsgames.co/randomusers/assets/avatars/male/60.jpg', 'luisem@il', 'Luis', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Reta', 'lreta'),
(5, 'https://xsgames.co/randomusers/assets/avatars/female/68.jpg', 'mariaem@il', 'María', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Fernandez', 'mfernandez'),
(6, 'https://xsgames.co/randomusers/assets/avatars/male/54.jpg', 'robertoem@il', 'Roberto', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Martinez', 'rmartinez'),
(7, 'https://xsgames.co/randomusers/assets/avatars/female/17.jpg', 'anaem@il', 'Ana', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Melendez', 'amelendez'),
(8, 'https://xsgames.co/randomusers/assets/avatars/male/43.jpg', 'albertoem@il', 'Alberto', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Lopez', 'alopez'),
(9, 'https://xsgames.co/randomusers/assets/avatars/female/77.jpg', 'mercedesem@il', 'Mercedes', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Mila', 'mmila'),
(10, 'https://xsgames.co/randomusers/assets/avatars/male/26.jpg', 'ramonem@il', 'Ramón', '$2a$10$dsQX4tLUoI9qFpRXhdRYcOpM1ORFAU60Jtr/WSn.g0mY6ADvZsa5q', 'Perez', 'rperez');

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `cinema`
--

INSERT INTO `cinema` (`id`, `image`, `cif`, `email`, `facebook`, `instagram`, `linked_in`, `name`, `phone`, `twitter`, `url`, `address_id`) VALUES
(1, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/19/61/3d/65/entrada.jpg?w=1200&h=-1&s=1', '123456987', 'cinesaana@cine.com', 'Facebook1', 'Instagram1', 'LinkedIn1', 'Cines Aana', '693326598', 'Twitter1', 'http://www.cinesaana.com/', 1),
(2, 'https://imagenes.20minutos.es/files/og_thumbnail/uploads/imagenes/2013/04/18/c9f3117_CINES_RENOIR.jpg', '987456321', 'princesa@cine.com', 'Facebook2', 'Instagram2', 'LinkedIn2', 'Reinor Princesa', '658946785', 'Twitter2', 'https://www.cinesrenoir.com/cartelera/cine/349566086E5F21EF/', 2),
(3, 'https://fastly.4sqi.net/img/general/600x600/1285676_1ma4wO1IhSyhcsP-RIWxYrVPpKTRqQf7IAQ_gaxfP_E.jpg', '654987123', 'pamplona@cine.com', 'Facebook3', 'Instagram3', 'LinkedIn3', 'Golem Bayona', '685269831', 'Twitter3', 'https://golem.es/golem/golem-baiona', 3),
(4, 'https://static2.hoy.es/www/multimedia/202101/20/media/cortadas/157188370--1248x936.jpg', '951753682', 'donbenito@cine.com', 'Facebook4', 'Instagram4', 'LinkedIn4', 'Cines Victoria Don Benito', '647958969', 'Twitter4', 'http://www.cinesvictoria.com/', 4),
(5, 'https://dades.grupnaciodigital.cat/redaccio/arxius/imatges/202006/1200_1592410050yelmosantcugat.jpg', '951753680', 'yelmoabrera@cine.com', 'Facebook5', 'Instagram5', 'LinkedIn5', 'Cine Yelmo Abrera', '647958960', 'Twitter5', 'http://www.yelmocines.es/', 5);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `film`
--

INSERT INTO `film` (`id`, `actor`, `classification`, `company`, `country`, `director`, `duration`, `music`, `photography`, `poster`, `rating`, `synopsis`, `script`, `title`, `trailer`, `year`) VALUES
(1, 'Paolo Virzì, Domitilla D`Amico', 1, '3D Produzioni Nexo Digital', 'Italia', 'Valeria Parisi', 90, 'Dmitri Myachin Maximilien Zaganelli', 'Sabina Bologna Lorenzo Giromini', 'https://www.bizcochito.es/Films/Poster/tt12035456.webp', 4, 'Con motivo del centenario de la muerte de Modigliani esta película documental explora la vida y obra de Amedeo Modigliani (1884-1920) un pintor de vanguardia que se convirtió en un artista contemporáneo clásico amado e imitado en todo el mundo.', 'Arianna Marelli Valeria Parisi', 'El indomable Modigliani', 'https://www.youtube.com/watch?v=GcCjxp7XjJo', 2020),
(2, 'Sean Bean, Eleanor Tomlinson', 0, 'ATres Media', 'España',' García Galocha' ,88, 'Fernando Velazquez', 'Animacion', 'https://mula.es/web/wp-content/uploads/2023/03/Poster-de-la-pelicuula-Momias.jpeg', 4, 'En las entrañas de la tierra, ¡existe una ciudad de momias! Por mandato imperial, la Princesa Nefer debe casarse con Thut, un ex auriga de carros. Ninguno desea el matrimonio; Nefer porque ansía la libertad, y Thut porque es alérgico al matrimonio', 'Jordi Gasull', 'Momias', 'https://www.youtube.com/watch?v=-K6R-J3PjpE', 2023),
(3, 'Clark Gable, Olivia de Havilland', 2, 'Metro-Goldwyn-Mayer (MGM)','Estados Unidos', 'Victor Fleming George Cukor Sam Wood', 238, 'Max Steiner', 'Ernest Haller', 'https://static.carrefour.es/hd_510x_/imagenes/products/21000/00214/009/2100000214009/imagenGrande1.jpg', 5,'En la elegante mansión sureña de Tara vive Scarlett OHara  la joven más bella caprichosa y egoísta de la región', 'Sidney Howard Oliver H.P. Garrett', 'Lo que el viento se llevó','https://www.youtube.com/watch?v=LWB4ESEsnMY' , 1939),
(4, 'Lauren LaVera, David Howard Thornton', 4, 'Dark Age Cinema','Estados Unidos', 'Damien Leone', 138,'Paul Wiley', 'George Steuber','https://cdn.shopify.com/s/files/1/0565/6730/0304/products/ScreenShot2022-12-07at4.27.17PM_530x@2x.png?v=1670448679' ,2, 'Después de ser resucitado por una entidad siniestra Art the Clown regresa al condado de Miles donde debe cazar y destruir a una adolescente y a su hermano menor en la noche de Halloween.','Damien Leone', 'Terrifier 2', 'https://www.youtube.com/watch?v=6KkONLf_ZKU', 2022),
(5, 'Michelle Williams, Gabriel LaBelle', 3, 'Universal Pictures', 'Estados Unidos', 'Steven Spielberg', 151, 'John Williams', 'Janusz Kaminski', 'https://i.etsystatic.com/25432943/r/il/6c4be3/4428552271/il_1140xN.4428552271_fma8.jpg', 4, 'Film semiautobiográfico de la propia infancia y juventud de Spielberg. Ambientada a finales de la década de 1950 y principios de los años 60, un niño de Arizona llamado Sammy Fabelman, influido por su excéntrica madre', 'Tony Kushner_Steven Spielberg', 'Los Fabelman', 'https://www.youtube.com/watch?v=PSpWKovHFf8&t=6s', 2022),
(6, 'Carla Campra, Marc Soler', 3, 'Ikiru Films', 'España', 'Victor Garcia', 98, 'Marc Timón', 'José Luis Bernal Ibañez', 'https://img.aullidos.com/imagenes/caratulas/nina-comunion-poster-2.jfif', 3, 'Una noche van a una discoteca, toman drogas y durante el trayecto a casa encontrarán una muñeca vestida de comunión. A partir de ese momento, comenzará la pesadilla.', 'Guillem Clua', 'La niña de la Comunión', 'https://www.youtube.com/watch?v=Xr91d6IKN-I', 2022),
(7, 'Gerard Butler, Mike Colter', 4, 'Coproducción Estados Unidos', 'Estados Unidos', 'Jean-Francois Richet', 107, 'Marco Beltrami', 'Brendan Galvin', 'https://cinesimf.com/uploads/peliculas/fdc05126a2fa02278e705bf7b05833c0.jpg', 3, 'En la noche de fin de año, el piloto experto Brodie Torrance (Gerard Butler) realiza un arriesgado aterrizaje cuando su avión, repleto de pasajeros, es alcanzado por un rayo.', 'Matt Cook', 'El Piloto', 'https://www.youtube.com/watch?v=7AGyfcwVssE', 2023),
(8, 'Emilio Sakraya', 4, 'Warner Broos', 'Alemania', 'Faith Akin', 138, 'Ralf Kemper', 'Rainer Klausman', 'https://es.web.img3.acsta.net/pictures/21/04/20/13/56/3142292.jpg', 2, 'Narra el camino del rapero Xatar desde el gueto hasta llegar a la cima de las listas musicales, viviendo por el camino una dramática historia de aventuras.', 'Fatih Akin', 'Oro Puro', 'https://www.youtube.com/watch?v=dOCcRATQ4x8', 2022),
(9, 'Leonardo DiCaprio, Kate Winslet,', 2, 'Paramount Pictures', 'Estados Unidos', 'James Cameron', 195, 'James Horner', 'Russell Carpenter', 'https://i.etsystatic.com/27475238/r/il/eead90/3678021029/il_fullxfull.3678021029_d6bp.jpg', 4, 'Jack (DiCaprio), un joven artista, gana en una partida de cartas un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido.', 'James Cameron', 'Titanic', 'https://www.youtube.com/watch?v=tA_qMdzvCvk', 1997),
(10, 'Animación', 1, 'Daysview Animation', 'China', 'Jianming Huang', 96, 'Xiaoyu Wu', 'Animación', 'https://www.lahiguera.net/cinemania/pelicula/10563/mi_querido_monstruo-cartel-10885.jpg', 4, 'Para curar a Hei Ling de una enfermedad terminal, el curandero de Kunlun, Bai Ze, es desterrado tras destruir accidentalmente una isla. Siete años después, regresa a la isla para buscar la redención.', 'Liang Li', 'Mi Querido Monstruo', 'https://www.youtube.com/watch?v=jxEaBgqnjxw', 2022),
(11, 'Virginie Efira, Roschdy Zem', 4, 'Les Films Velvet', 'Francia', 'Rebecca Zlotowski', 104, 'Robin Coudert', 'Georges Lechaptois', 'https://www.lahiguera.net/cinemania/pelicula/10344/los_hijos_de_otros-cartel-10846.jpg', 3, 'Rachel tiene 40 años, no tiene hijos. Ella ama su vida: sus estudiantes de secundaria, sus amigos, sus ex, sus lecciones de guitarra.', 'Rebeca Zkitiwski', 'Los hijos de otros', 'https://www.youtube.com/watch?v=KugOKTgTA1c', 2022),
(12, 'Edurne Azkarate, Eneko Sagardoy', 2, 'Ikusgarri Films ETB', 'España','Paul Urkijo Alijo' ,111, 'Maite Arroitajauregi Aránzazu Calleja', 'Gorka Gómez Andreu', 'https://gipuzkoadigital.com/wp-content/uploads/2020/06/IRATI-TEASER-POSTER.jpg', 3,'Siglo VIII. El cristianismo se extiende por Europa mientras las creencias paganas desaparecen. Ante el ataque del ejército de Carlomagno atravesando los Pirineos, el líder del valle pide ayuda a una diosa ancestral.', 'Paul Urkijo Alijo', 'Irati', 'https://www.youtube.com/watch?v=Ib6nnBHtnEA', 2022),
(13, 'Nat Kitcharit, Urassaya Sperbund', 2, 'Happy Ending Film','Tailandia', 'Nawapol Thamrongrattanarit', 132, 'Nawapol Thamrongrattanarit', 'Natdanai Naksuwan', 'https://www.lavanguardia.com/peliculas-series/images/movie/poster/2022/4/w1280/qA4gcxdeuVsVgnrMxCmHNVF6cQH.jpg', 3,'Cuando a un campeón mundial de apilamiento deportivo le deja su novia de toda la vida tiene que aprender las habilidades básicas de un adulto para poder vivir solo y cuidar de sí mismo.' , 'Nawapol Thamrongrattanarit', 'Amor contrarreloj','https://www.youtube.com/watch?v=pg_Ij-3YySM', 2022),
(14, 'Marilyn Monroe, Jack Lemmon', 0, 'The Mirisch Corporation','Estados Unidos', 'Billy Wilder', 120, 'Adolph Deutsch', 'Charles Lang (B&W)', 'https://i.pinimg.com/originals/90/4f/bc/904fbcc1896341a3dbdfd00ae301a642.jpg', 5,'Época de la Ley Seca (1920-1933). Joe y Jerry son dos músicos del montón que se ven obligados a huir después de ser testigos de un ajuste de cuentas entre dos bandas rivales', 'Billy Wilder I.A.L. Diamond', 'Con faldas y a lo loco','https://www.youtube.com/watch?v=tyJ_1ZXRHdc', 1959),
(15, 'Robert Pattinson, Zöe Kravitz', 2, 'Universal', 'Estados Unidos', 'Matt Reeves', 135, 'Michael Giacchino', 'Greig Fraser', 'https://www.themoviedb.org/t/p/original/mo7teil1qH0SxgLijnqeYP1Eb4w.jpg', 3, 'Después de dos años acechando por las calles de la ciudad como Batman (Robert Pattinson), e infundiendo miedo en las mentes perversas de los criminales, Bruce Wayne está sumido en las profundidades de las sombras de Gotham City', 'Guionista', 'Batman', 'https://www.youtube.com/watch?v=gRdF4mbJ4Vo', 2022),
(16, 'Storm Reid, Nia Long', 4, 'Sony Pictures Entertainment (SPE)', 'Estados Unidos', 'Nicholas D. Johnson',111, 'Julian Scherle', 'Steven Holleran', 'https://guiadecadiz.com/sites/default/files/peliculas/2023/febrero/missing.jpg',3, 'Cuando su madre desaparece estando de vacaciones en Colombia con su nuevo novio', 'Nicholas D. Johnson', 'Missing', 'https://www.youtube.com/watch?v=seBixtcx19E', 2023),
(17, 'Danielle Deadwyler, Jalyn Hall', 4, 'Metro-Goldwyn-Mayer (MGM)','Estados Unidos', 'Chinonye Chukwu', 131, 'Abel Korzeniowski', 'Bobby Bukowski','https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2023/02/till-crimen-cambio-todo-2969628.jpg?itok=9zbSmpzL' , 2,'La historia de Emmett Louis Till y el legado de su madre, que buscó justicia para su hijo asesinado' , 'Keith Beauchamp', 'Till, el crimen que lo cambió todo', 'https://www.youtube.com/watch?v=8OdgDTFHICg', 2022),
(18, 'Franck Dubosc, Louna Espinosa', 2, 'Coproducción Francia-Bélgica','Francia', 'Franck Dubosc', 102, 'Sylvain Goldberg', 'Ludovic Colbeau-Justin','https://estaticos-cdn.prensaiberica.es/clip/27eb1994-8bb8-4a01-9b45-e3ecdad72976_9-16-aspect-ratio_default_0.jpg' , 1,'La historia de un padre que hará todo lo posible para conectarse con su hija' , 'Franck Dubosc', 'Rumba terapia','https://www.youtube.com/watch?v=HqaAZ9_WfXU' , 2022),
(19, 'Riho Yoshioka, Tomoya Nakamura', 3, 'Toei','Japón', 'Kohei Yoshino', 128, 'Mizuki Tsujimura', 'Motonobu kiyoku', 'https://www.ecartelera.com/carteles/17600/17687/001.jpg', 2, 'Hitomi Saito trabaja para una importante productora de animación en Japón. Renunció a una carrera estable como funcionaria para unirse a la industria de anime.', 'Yosuke Masaike', 'Anime Supremacy!','https://www.youtube.com/watch?v=hgW2IzwZ9vc' , 2022),
(20, 'Colin Farrell, Brendan Gleeson', 2, 'Walt Disney Pictures', 'Reino Unido', 'Martin McDonagh', 114, 'Carter Burwell', 'Ben Davis','https://pozoblanco.es/wp-content/uploads/2023/02/329433822_550103017084889_6778719242366054488_n-1.jpg', 3,'Ambientada en una isla remota frente a la costa oeste de Irlanda, ‘Almas en pena de Inisherin’ cuenta la historia de dos amigos de toda la vida, Pádraic y Colm, quienes se encuentran en un callejón sin salida', 'Martin McDonagh', 'Almas en pena de Inisherin','https://www.youtube.com/watch?v=K-SC025zQ5A', 2022),
(21, 'Dave Bautista, Jonathan Groff', 3, 'Universal Pictures', 'Estados Unidos', 'M. Night Shyamalan', 100, 'Herdís Stefánsdóttir', 'Jarin Blaschke', 'https://es.web.img2.acsta.net/pictures/22/12/05/18/32/5169034.png', 2, 'Durante unas vacaciones en una cabaña en un bosque alejada de todo, una niña y sus padres se convierten en rehenes de cuatro desconocidos armados que obligan a la familia a tomar una decisión imposible para evitar el apocalipsis.', 'M. Night Shyamalan', 'Llaman a la puerta', 'https://www.youtube.com/watch?v=iS_eDGU9Tkw', 2023),
(22, 'Guillaume Canet, Gilles Lellouche', 0, 'Les Editions Albert René, Les Enfants Terribles, Pathé, Trésor Films', 'Francia', 'Guillaume Canet', 111, 'Matthie Chedid', 'Andre Chemetoff', 'https://www.lahiguera.net/cinemania/pelicula/10535/asterix_y_obelix_y_el_reino_medio-cartel-10887.jpg', 5, 'Es el año 50 A.C., la Emperatriz de China acaba de ser encarcelada tras un golpe de Estado incitado por Dang Sin Kuing, un príncipe traidor. Ayudada por Granodemaíz, el comerciante fenicio, y su fiel guardaespaldas Wang Tah, la única hija de la Emperatriz', 'Guillaume Canet, Julien Hervé, Philippe Mechelen.', 'Astérix y Obélix y el reino medio', 'https://www.youtube.com/watch?v=xdp4hc1u9LA', 2023),
(23, 'Michelle Yeoh, Ke Huy Quan', 2, 'AGBO, Hotdog Hands, Ley Line Entertainment, Year of The Rat, IAC Films, A24.', 'Estados Unidos', 'Dan Kwan, Daniel Scheinert, Daniels', 139, 'Son Lux', 'Larkin Seiple','https://es.web.img3.acsta.net/pictures/22/04/26/16/27/2834053.jpg' , 2, 'Cuando una ruptura interdimensional altera la realidad, Evelyn (Michelle Yeoh), una inmigrante china en Estados Unidos, se ve envuelta en una aventura salvaje en la que solo ella puede salvar el mundo. Perdida en los mundos infinitos del multiverso', 'Dan Kwan, Daniel Scheinert', 'Todo a la vez en todas partes', 'https://www.youtube.com/watch?v=BVRZ_QLxFNw', 2022),
(24, 'Catherine Clinch, Carrie Crowley', 4, 'Inscéal, Broadcasting Authority of Ireland, TG4, Fís Éireann/Screen, Screen Ireland', 'Irlanda', 'Colm Bairéad', 95, 'Stephen Rennicks', 'Kate McCullough', 'https://www.aceprensa.com/wp-content/uploads/2023/02/the-quiet-girl.webp', 2, 'La Irlanda rural, 1981. Cáit es una reservada niña de nueve años que está desatendida por parte de su pobre, disfuncional y demasiado numerosa familia. Se enfrenta en silencio con dificultades en la escuela y en casa.', 'Colm Bairéad. Historia Claire Keegan', 'The Quiet Girl', 'https://www.youtube.com/watch?v=ffmVqyA3yew', 2022),
(25, 'Marion Cotillard, Melvil Poupaud', 3, 'Why Not Productions, arte France Cinéma', 'Francia', 'Arnaud Desplechin', 108, 'Grégoire Hetzel', 'Irina Lubtchansky', 'https://estaticos-cdn.prensaiberica.es/clip/869f823e-f105-4cd6-8640-0bbdb4402c5b_9-16-aspect-ratio_default_0.jpg', 1, 'Un hermano y una hermana a punto de entrar en la cincuentena. Alice es actriz. Louis fue profesor y poeta. Alice odia a su hermano desde hace más de veinte años. No se han visto en todo ese tiempo. Debido a la muerte de sus padres.', 'Arnaud Desplechin, Julie Peyr', 'Asuntos familiares','https://www.youtube.com/watch?v=0qckQ3FoFt4' , 2022),
(26, 'Documental', 2, 'Telecinco', 'España', 'José María Zavala', 78, 'Luis Mas', 'Georges Lechaptois', 'https://es.web.img2.acsta.net/pictures/22/10/28/14/44/1112841.jpg', 1, 'Película biográfica sobre el beato Carlo Acutis, un estudiante italiano y aficionado programador de informática, conocido por documentar milagros eucarísticos alrededor del mundo y catalogarlos en un sitio web que creó antes de su muerte.', 'José María Zavala', 'El cielo no puede esperar', 'https://www.youtube.com/watch?v=W4K_ClZ4o2U', 2022),
(27, 'Animación', 0, 'Magic Light Pictures, BBC One, BBC iPlayer, ZDF', 'Reino Unido', 'Samantha Cutler, Daniel Snaddon', 26, 'René Aubry', 'Animación', 'https://cinesembajadores.es/wp-content/uploads/2023/02/bill-i-janet_WEB1200X1800_ESP.jpg', 2, 'Bill y Janet se enamoran mientras sus familias están en guerra. Ante la desaprobación de su entorno, huyen a un planeta lejano y sus familias deben dejar de lado sus diferencias y trabajar juntos para traerlos de regreso a casa.', 'Julia Smuts Louw', 'Bill y Janet y otras crónicas marcianas', 'https://www.youtube.com/watch?v=r2Iwz-Ik8pA', 2022),
(28, 'Clara Gostynski, Alexei Evstratov', 4, 'Seeland Filmproduktion', 'Suiza', 'Cyril Schäublin', 93, 'Li Tavor', 'Silvan Hillmann', 'https://cineuropa.org/Galleries/421/164/poster_big.jpg?1643807113104', 3, 'Las nuevas tecnologías están transformando una ciudad relojera del siglo XIX en Suiza. Josephine, una joven trabajadora de una fábrica, produce la rueda de disturbios, que gira en el corazón del reloj mecánico.', 'Cyril Schäublin', 'Disturbios', 'https://www.youtube.com/watch?v=9QUbXovP2HY', 2022),
(29, 'Documental', 3, 'Coproducción España-Bosnia y Herzegovina. Dynamite Films, Udruzenje Mirza Delibasic - Kindje', 'España', 'Juan Gautier', 93, 'Luis Herrero', 'Lamia Sabic', 'https://i0.wp.com/noescinetodoloquereluce.com/wp-content/uploads/2023/02/shooting.jpg?ssl=1', 3, 'Mirza Delibašić reinó en el baloncesto europeo de los años 70 y primeros 80. Su clase y su elegancia al frente del Bosnia Sarajevo, del Real Madrid y de la mítica selección yugoslava le convirtieron en una leyenda.', 'Juan Gautier', 'Shooting for Mirza', 'https://www.youtube.com/watch?v=TtXnftMMZsA', 2021),
(30, 'Pedro Hestnes, Inês de Medeiros', 4, 'Fundação Calouste Gulbenkian, Tropico Filmes, Radiotelevisão Portuguesa, Instituto Português de Cinema (IPC)', 'Portugal', 'Pedro Costa', 95, 'Acácio de Almeida', 'Acácio de Almeida, Elso Roque, Martin Schäfer (B&W)', 'https://es.web.img2.acsta.net/pictures/23/01/31/15/43/5269091.jpg', 3, 'Dos hermanos, Nino y Vincente, han aprendido a desenvolverse muy bien juntos durante las inexplicadas ausencias de su padre. El día que aparentemente desaparece para siempre, no tienen ningún problema en seguir como antes.', 'Pedro Costa', 'La sangre','https://www.youtube.com/watch?v=RBttCtfsCQg', 1989),
(31, 'Documental', 2, 'Filmstudio Berlin', 'Alemania', 'Robert Siodmak, Edgar G. Ulmer, Curt Siodmak, Fred Zinnemann', 74, 'Billy Wilder', 'Eugen Schüfftan (B&W)', 'https://www.deutsche-kinemathek.de/sites/default/files/public/styles/sdk-medium/public/Publikationen/DVDs/siodmak_menschen_am_sonntag.jpg?itok=irLKuSI7', 1, 'Una mezcla de ficción y documental que retrata a los berlineses de 1930 durante un domingo. Obtuvo muy buenas críticas y dio la oportunidad a sus creadores (Siodmak, Ulmer, Wilder, Zinnemann) de dar el salto al cine de ficción.', 'Billy Wilder, Curt Siodmak, Robert Siodmak', 'Gente en domingo','https://www.youtube.com/watch?v=djsAduducm0', 1930),
(32, 'Paul Rudd, Evangeline Lilly', 3, 'Marvel Studios, Walt Disney Pictures, Truenorth Productions. Distribuidora: Walt Disney Pictures', 'Estados Unidos', 'Peyton Reed', 125, 'Christophe Beck', 'Bill Pope', 'https://sm.ign.com/ign_es/screenshot/default/ant-man-and-the-wasp-quantumania-posters-individuales-ant-ma_j1ru.png', 5, 'Scott Lang y Hope Van Dyne, junto con Hank Pym y Janet Van Dyne, exploran el Reino Cuántico, donde interactúan con extrañas criaturas y se embarcan en una aventura que va más allá de los límites de lo que creían posible.', 'Jeff Loveness. Cómic: Stan Lee, Jack Kirby, Larry Lieber', 'Ant-Man y la Avispa: Quantumanía', 'https://www.youtube.com/watch?v=BaLJ044I2HI', 2023);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `film_genders`
--

INSERT INTO `film_genders` (`film_id`, `genders`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 1),
(6, 6),
(7, 2),
(8, 2),
(9, 8),
(10, 9),
(11, 1),
(12, 1),
(13, 6),
(14, 0),
(15, 2);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`id`, `active`, `capacity`, `premiere`, `room_number`, `cinema_id`, `film_id`) VALUES
(null, b'1', 130, '2024-05-15', 1, 1, 1),
(null, b'1', 130, '2024-05-15', 2, 1, 2),
(null, b'1', 130, '2024-05-15', 3, 1, 3),
(null, b'1', 130, '2024-05-15', 4, 1, 4),
(null, b'1', 130, '2024-05-15', 5, 1, 5),

(null, b'1', 130, '2024-05-15', 1, 2, 1),
(null, b'1', 130, '2024-05-15', 2, 2, 2),
(null, b'1', 130, '2024-05-15', 3, 2, 3),
(null, b'1', 130, '2024-05-15', 4, 2, 4),
(null, b'1', 130, '2024-05-15', 5, 2, 5),

(null, b'1', 130, '2024-05-15', 1, 3, 1),
(null, b'1', 130, '2024-05-15', 2, 3, 2),
(null, b'1', 130, '2024-05-15', 3, 3, 3),
(null, b'1', 130, '2024-05-15', 4, 3, 4),
(null, b'1', 130, '2024-05-15', 5, 3, 5),

(null, b'1', 130, '2024-05-15', 1, 4, 1),
(null, b'1', 130, '2024-05-15', 2, 4, 2),
(null, b'1', 130, '2024-05-15', 3, 4, 3),
(null, b'1', 130, '2024-05-15', 4, 4, 4),
(null, b'1', 130, '2024-05-15', 5, 4, 5),

(null, b'1', 130, '2024-05-15', 1, 5, 1),
(null, b'1', 130, '2024-05-15', 2, 5, 2),
(null, b'1', 130, '2024-05-15', 3, 5, 3),
(null, b'1', 130, '2024-05-15', 4, 5, 4),
(null, b'1', 130, '2024-05-15', 5, 5, 5);

-- --------------------------------------------------------

--
-- Volcado de datos para la tabla `room_schedules`
--

INSERT INTO `room_schedules` (`room_id`, `schedules`) VALUES
(1, '16:30'),
(2, '16:30'),
(3, '16:30'),
(4, '16:30'),
(5, '16:30'),
(6, '16:30'),
(7, '16:30'),
(8, '16:30'),
(9, '16:30'),
(10, '16:30'),
(11, '16:30'),
(12, '16:30'),
(13, '16:30'),
(14, '16:30'),
(15, '16:30'),
(16, '16:30'),
(17, '16:30'),
(18, '16:30'),
(19, '16:30'),
(20, '16:30'),
(21, '16:30'),
(22, '16:30'),
(23, '16:30'),
(24, '16:30'),
(25, '16:30'),
(1, '18:30'),
(2, '18:30'),
(3, '18:30'),
(4, '18:30'),
(5, '18:30'),
(6, '18:30'),
(7, '18:30'),
(8, '18:30'),
(9, '18:30'),
(10, '18:30'),
(11, '18:30'),
(12, '18:30'),
(13, '18:30'),
(14, '18:30'),
(15, '18:30'),
(16, '18:30'),
(17, '18:30'),
(18, '18:30'),
(19, '18:30'),
(20, '18:30'),
(21, '18:30'),
(22, '18:30'),
(23, '18:30'),
(24, '18:30'),
(25, '18:30'),
(1, '20:30'),
(2, '20:30'),
(3, '20:30'),
(4, '20:30'),
(5, '20:30'),
(6, '20:30'),
(7, '20:30'),
(8, '20:30'),
(9, '20:30'),
(10, '20:30'),
(11, '20:30'),
(12, '20:30'),
(13, '20:30'),
(14, '20:30'),
(15, '20:30'),
(16, '20:30'),
(17, '20:30'),
(18, '20:30'),
(19, '20:30'),
(20, '20:30'),
(21, '20:30'),
(22, '20:30'),
(23, '20:30'),
(24, '20:30'),
(25, '20:30'),
(1, '22:30'),
(2, '22:30'),
(3, '22:30'),
(4, '22:30'),
(5, '22:30'),
(6, '22:30'),
(7, '22:30'),
(8, '22:30'),
(9, '22:30'),
(10, '22:30'),
(11, '22:30'),
(12, '22:30'),
(13, '22:30'),
(14, '22:30'),
(15, '22:30'),
(16, '22:30'),
(17, '22:30'),
(18, '22:30'),
(19, '22:30'),
(20, '22:30'),
(21, '22:30'),
(22, '22:30'),
(23, '22:30'),
(24, '22:30'),
(25, '22:30');

-- --------------------------------------------------------

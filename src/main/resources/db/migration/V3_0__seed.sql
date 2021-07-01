INSERT INTO public._cart(
    id, created_at)
VALUES (1, now());

INSERT INTO public._wallet(
    id, created_at, value)
VALUES (1, now(), 0);

INSERT INTO public._person(
    dtype, id, created_at, active, deleted_at, email, encrypted_password, name, roles, telephone, cart_id, customer_type_id, wallet_id)
VALUES ('Customer', 1, now(), true, null, 'admin@12345.com', '$2a$10$guAIXcUuPWgHx7o2Jr45Q.QAE/xii0hhT0tkUwuATwdGzgS44DY2.', 'Administrador A.M. Store', 'ROLE_ADMIN', '(11) 91234-5678', 1, 1, 1);

INSERT INTO public._game(
    id, created_at, active, age, amount, amount_available, image, multiplayer, price, release_date, sinopsis, title, platform_id, publisher_id, reason)
VALUES (1, now(), true, 14, 100, 100, 'http://assets.vg247.com/current/2016/06/watch_dog_logo.jpg', true, 50, '2021-02-18', 'Jogo de Hacking.', 'Watch Dogs', 1, 1, null);

INSERT INTO public._game(
    id, created_at, active, age, amount, amount_available, image, multiplayer, price, release_date, sinopsis, title, platform_id, publisher_id, reason)
VALUES (2, now(), true, 21, 39, 38, 'https://cdn.cloudflare.steamstatic.com/steam/apps/307780/capsule_616x353.jpg?t=1588110604', true, 99, '2014-10-10', 'Jogo de Luta.', 'Mortal Kombat X', 1, 2, null);

INSERT INTO public._game(
    id, created_at, active, age, amount, amount_available, image, multiplayer, price, release_date, sinopsis, title, platform_id, publisher_id, reason)
VALUES (3, now(), true, 21, 26, 25, 'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwallpapercave.com%2Fwp%2Fwp5825925.jpg&f=1&nofb=1', true, 200, '2019-08-30', 'IT’S A CHAOTIC NEW WORLD Verdansk and the Co_ld War have collided, plunging players into a chaotic new world. Season Three of Warzone bursts onto the scene with a new look for Call of Duty', 'Call of Duty Warzone', 3, 2, null);

INSERT INTO public._game(
    id, created_at, active, age, amount, amount_available, image, multiplayer, price, release_date, sinopsis, title, platform_id, publisher_id, reason)
VALUES (4, now(), true, 10, 32, 26, 'https://amorgames.com.br/product_images/h/335/need-for-speed-heat__07728_zoom.jpg', true, 200, '2019-08-30', 'Need for Speed Heat is a racing video game developed by Ghost Games and published by Electronic Arts for Microsoft Windows, PlayStation 4 and Xbox One. It is the twenty-fourth installment in the Need for Speed series and commemorates the series', 'Final Fantasy XV', 3, 2, null);

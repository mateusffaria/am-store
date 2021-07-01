--
-- PostgreSQL database dump
--

-- Dumped from database version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.7 (Ubuntu 12.7-0ubuntu0.20.04.1)

-- Started on 2021-06-11 22:46:03 -03

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 59746)
-- Name: _address; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._address (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    complement character varying(255),
    deleted_at timestamp without time zone,
    number character varying(255) NOT NULL,
    postal_code character varying(255) NOT NULL,
    street character varying(255) NOT NULL,
    address_type_id bigint NOT NULL,
    city_id bigint NOT NULL,
    person_id bigint
);


--
-- TOC entry 203 (class 1259 OID 59754)
-- Name: _address_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._address_type (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    name character varying(255)
);


--
-- TOC entry 204 (class 1259 OID 59762)
-- Name: _banner; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._banner (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 205 (class 1259 OID 59767)
-- Name: _cart; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._cart (
    id bigint NOT NULL,
    created_at timestamp without time zone
);


--
-- TOC entry 206 (class 1259 OID 59772)
-- Name: _cart_item_list; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._cart_item_list (
    cart_id bigint NOT NULL,
    item_list_id bigint NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 59775)
-- Name: _city; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._city (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    state_id bigint
);


--
-- TOC entry 208 (class 1259 OID 59780)
-- Name: _coupon; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._coupon (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    amount integer,
    code character varying(255) NOT NULL,
    value double precision,
    CONSTRAINT _coupon_amount_check CHECK ((amount >= 1)),
    CONSTRAINT _coupon_value_check CHECK ((value >= (1)::double precision))
);


--
-- TOC entry 209 (class 1259 OID 59787)
-- Name: _credit_card; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._credit_card (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    number character varying(255),
    owner character varying(255),
    owner_doc character varying(255),
    security_code character varying(255),
    validade_month character varying(255),
    validade_year character varying(255),
    banner_id bigint,
    customer_id bigint
);


--
-- TOC entry 210 (class 1259 OID 59795)
-- Name: _customer_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._customer_type (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    title character varying(255)
);


--
-- TOC entry 211 (class 1259 OID 59803)
-- Name: _devolution; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._devolution (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    answer character varying(255),
    reason character varying(254) NOT NULL,
    status_devolution integer,
    order_id bigint
);


--
-- TOC entry 212 (class 1259 OID 59811)
-- Name: _document; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._document (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    code character varying(255) NOT NULL,
    deleted_at timestamp without time zone,
    validate date NOT NULL,
    document_type_id bigint NOT NULL,
    person_id bigint
);


--
-- TOC entry 213 (class 1259 OID 59816)
-- Name: _document_type; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._document_type (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


--
-- TOC entry 214 (class 1259 OID 59824)
-- Name: _game; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._game (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    active boolean NOT NULL,
    age integer NOT NULL,
    amount integer,
    amount_available integer,
    image character varying(255) NOT NULL,
    multiplayer boolean NOT NULL,
    price double precision NOT NULL,
    reason character varying(255),
    release_date date,
    sinopsis character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    platform_id bigint,
    publisher_id bigint
);


--
-- TOC entry 215 (class 1259 OID 59832)
-- Name: _game_genders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._game_genders (
    game_id bigint NOT NULL,
    genders_id bigint NOT NULL
);


--
-- TOC entry 216 (class 1259 OID 59835)
-- Name: _game_languages; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._game_languages (
    game_id bigint NOT NULL,
    languages_id bigint NOT NULL
);


--
-- TOC entry 217 (class 1259 OID 59838)
-- Name: _gender; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._gender (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 218 (class 1259 OID 59843)
-- Name: _gender_game; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._gender_game (
    gender_id bigint NOT NULL,
    game_id bigint NOT NULL
);


--
-- TOC entry 219 (class 1259 OID 59846)
-- Name: _item; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._item (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    amount integer,
    game_id bigint
);


--
-- TOC entry 220 (class 1259 OID 59851)
-- Name: _language; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._language (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 221 (class 1259 OID 59856)
-- Name: _language_game; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._language_game (
    language_id bigint NOT NULL,
    game_id bigint NOT NULL
);


--
-- TOC entry 222 (class 1259 OID 59859)
-- Name: _order; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._order (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    code character varying(255),
    delivery_date date,
    shipping_tax double precision,
    total double precision,
    address_id bigint,
    address_billing_id bigint,
    coupon_id bigint,
    customer_id bigint,
    status_id bigint,
    tracking_id bigint
);


--
-- TOC entry 223 (class 1259 OID 59864)
-- Name: _order_item_list; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._order_item_list (
    order_id bigint NOT NULL,
    item_list_id bigint NOT NULL
);


--
-- TOC entry 224 (class 1259 OID 59867)
-- Name: _order_payment_method_list; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._order_payment_method_list (
    order_id bigint NOT NULL,
    payment_method_list_id bigint NOT NULL
);


--
-- TOC entry 225 (class 1259 OID 59870)
-- Name: _payment_method; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._payment_method (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    payment_value double precision,
    credit_card_id bigint
);


--
-- TOC entry 226 (class 1259 OID 59875)
-- Name: _person; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._person (
    dtype character varying(31) NOT NULL,
    id bigint NOT NULL,
    created_at timestamp without time zone,
    active boolean NOT NULL,
    deleted_at timestamp without time zone,
    email character varying(255) NOT NULL,
    encrypted_password character varying(255),
    name character varying(255) NOT NULL,
    roles character varying(255),
    telephone character varying(255) NOT NULL,
    cart_id bigint,
    customer_type_id bigint,
    wallet_id bigint
);


--
-- TOC entry 227 (class 1259 OID 59883)
-- Name: _platform; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._platform (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 228 (class 1259 OID 59888)
-- Name: _publisher; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._publisher (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    name character varying(255)
);


--
-- TOC entry 229 (class 1259 OID 59893)
-- Name: _state; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._state (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    description character varying(255),
    uf character varying(255)
);


--
-- TOC entry 230 (class 1259 OID 59901)
-- Name: _status; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._status (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    status character varying(255)
);


--
-- TOC entry 231 (class 1259 OID 59906)
-- Name: _stock; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._stock (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    amo_integer integer NOT NULL,
    product_code character varying(255) NOT NULL,
    game_id bigint
);


--
-- TOC entry 232 (class 1259 OID 59911)
-- Name: _tracking; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._tracking (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    tracking character varying(255)
);


--
-- TOC entry 233 (class 1259 OID 59916)
-- Name: _wallet; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public._wallet (
    id bigint NOT NULL,
    created_at timestamp without time zone,
    value double precision NOT NULL
);


--
-- TOC entry 234 (class 1259 OID 59925)
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 3184 (class 0 OID 59746)
-- Dependencies: 202
-- Data for Name: _address; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._address (id, created_at, complement, deleted_at, number, postal_code, street, address_type_id, city_id, person_id) FROM stdin;
\.


--
-- TOC entry 3185 (class 0 OID 59754)
-- Dependencies: 203
-- Data for Name: _address_type; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._address_type (id, created_at, description, name) FROM stdin;
\.


--
-- TOC entry 3186 (class 0 OID 59762)
-- Dependencies: 204
-- Data for Name: _banner; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._banner (id, created_at, name) FROM stdin;
\.


--
-- TOC entry 3187 (class 0 OID 59767)
-- Dependencies: 205
-- Data for Name: _cart; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._cart (id, created_at) FROM stdin;
\.


--
-- TOC entry 3188 (class 0 OID 59772)
-- Dependencies: 206
-- Data for Name: _cart_item_list; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._cart_item_list (cart_id, item_list_id) FROM stdin;
\.


--
-- TOC entry 3189 (class 0 OID 59775)
-- Dependencies: 207
-- Data for Name: _city; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._city (id, created_at, description, state_id) FROM stdin;
\.


--
-- TOC entry 3190 (class 0 OID 59780)
-- Dependencies: 208
-- Data for Name: _coupon; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._coupon (id, created_at, amount, code, value) FROM stdin;
\.


--
-- TOC entry 3191 (class 0 OID 59787)
-- Dependencies: 209
-- Data for Name: _credit_card; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._credit_card (id, created_at, number, owner, owner_doc, security_code, validade_month, validade_year, banner_id, customer_id) FROM stdin;
\.


--
-- TOC entry 3192 (class 0 OID 59795)
-- Dependencies: 210
-- Data for Name: _customer_type; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._customer_type (id, created_at, description, title) FROM stdin;
\.


--
-- TOC entry 3193 (class 0 OID 59803)
-- Dependencies: 211
-- Data for Name: _devolution; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._devolution (id, created_at, answer, reason, status_devolution, order_id) FROM stdin;
\.


--
-- TOC entry 3194 (class 0 OID 59811)
-- Dependencies: 212
-- Data for Name: _document; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._document (id, created_at, code, deleted_at, validate, document_type_id, person_id) FROM stdin;
\.


--
-- TOC entry 3195 (class 0 OID 59816)
-- Dependencies: 213
-- Data for Name: _document_type; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._document_type (id, created_at, description, name) FROM stdin;
\.


--
-- TOC entry 3196 (class 0 OID 59824)
-- Dependencies: 214
-- Data for Name: _game; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._game (id, created_at, active, age, amount, amount_available, image, multiplayer, price, reason, release_date, sinopsis, title, platform_id, publisher_id) FROM stdin;
\.


--
-- TOC entry 3197 (class 0 OID 59832)
-- Dependencies: 215
-- Data for Name: _game_genders; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._game_genders (game_id, genders_id) FROM stdin;
\.


--
-- TOC entry 3198 (class 0 OID 59835)
-- Dependencies: 216
-- Data for Name: _game_languages; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._game_languages (game_id, languages_id) FROM stdin;
\.


--
-- TOC entry 3199 (class 0 OID 59838)
-- Dependencies: 217
-- Data for Name: _gender; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._gender (id, created_at, name) FROM stdin;
\.


--
-- TOC entry 3200 (class 0 OID 59843)
-- Dependencies: 218
-- Data for Name: _gender_game; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._gender_game (gender_id, game_id) FROM stdin;
\.


--
-- TOC entry 3201 (class 0 OID 59846)
-- Dependencies: 219
-- Data for Name: _item; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._item (id, created_at, amount, game_id) FROM stdin;
\.


--
-- TOC entry 3202 (class 0 OID 59851)
-- Dependencies: 220
-- Data for Name: _language; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._language (id, created_at, name) FROM stdin;
\.


--
-- TOC entry 3203 (class 0 OID 59856)
-- Dependencies: 221
-- Data for Name: _language_game; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._language_game (language_id, game_id) FROM stdin;
\.


--
-- TOC entry 3204 (class 0 OID 59859)
-- Dependencies: 222
-- Data for Name: _order; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._order (id, created_at, code, delivery_date, shipping_tax, total, address_id, address_billing_id, coupon_id, customer_id, status_id, tracking_id) FROM stdin;
\.


--
-- TOC entry 3205 (class 0 OID 59864)
-- Dependencies: 223
-- Data for Name: _order_item_list; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._order_item_list (order_id, item_list_id) FROM stdin;
\.


--
-- TOC entry 3206 (class 0 OID 59867)
-- Dependencies: 224
-- Data for Name: _order_payment_method_list; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._order_payment_method_list (order_id, payment_method_list_id) FROM stdin;
\.


--
-- TOC entry 3207 (class 0 OID 59870)
-- Dependencies: 225
-- Data for Name: _payment_method; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._payment_method (id, created_at, payment_value, credit_card_id) FROM stdin;
\.


--
-- TOC entry 3208 (class 0 OID 59875)
-- Dependencies: 226
-- Data for Name: _person; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._person (dtype, id, created_at, active, deleted_at, email, encrypted_password, name, roles, telephone, cart_id, customer_type_id, wallet_id) FROM stdin;
\.


--
-- TOC entry 3209 (class 0 OID 59883)
-- Dependencies: 227
-- Data for Name: _platform; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._platform (id, created_at, name) FROM stdin;
\.


--
-- TOC entry 3210 (class 0 OID 59888)
-- Dependencies: 228
-- Data for Name: _publisher; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._publisher (id, created_at, name) FROM stdin;
\.


--
-- TOC entry 3211 (class 0 OID 59893)
-- Dependencies: 229
-- Data for Name: _state; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._state (id, created_at, description, uf) FROM stdin;
\.


--
-- TOC entry 3212 (class 0 OID 59901)
-- Dependencies: 230
-- Data for Name: _status; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._status (id, created_at, status) FROM stdin;
\.


--
-- TOC entry 3213 (class 0 OID 59906)
-- Dependencies: 231
-- Data for Name: _stock; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._stock (id, created_at, amo_integer, product_code, game_id) FROM stdin;
\.


--
-- TOC entry 3214 (class 0 OID 59911)
-- Dependencies: 232
-- Data for Name: _tracking; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._tracking (id, created_at, tracking) FROM stdin;
\.


--
-- TOC entry 3215 (class 0 OID 59916)
-- Dependencies: 233
-- Data for Name: _wallet; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public._wallet (id, created_at, value) FROM stdin;
\.


--
-- TOC entry 3223 (class 0 OID 0)
-- Dependencies: 234
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- TOC entry 2968 (class 2606 OID 59753)
-- Name: _address _address_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._address
    ADD CONSTRAINT _address_pkey PRIMARY KEY (id);


--
-- TOC entry 2970 (class 2606 OID 59761)
-- Name: _address_type _address_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._address_type
    ADD CONSTRAINT _address_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2972 (class 2606 OID 59766)
-- Name: _banner _banner_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._banner
    ADD CONSTRAINT _banner_pkey PRIMARY KEY (id);


--
-- TOC entry 2974 (class 2606 OID 59771)
-- Name: _cart _cart_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._cart
    ADD CONSTRAINT _cart_pkey PRIMARY KEY (id);


--
-- TOC entry 2976 (class 2606 OID 59779)
-- Name: _city _city_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._city
    ADD CONSTRAINT _city_pkey PRIMARY KEY (id);


--
-- TOC entry 2978 (class 2606 OID 59786)
-- Name: _coupon _coupon_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._coupon
    ADD CONSTRAINT _coupon_pkey PRIMARY KEY (id);


--
-- TOC entry 2980 (class 2606 OID 59794)
-- Name: _credit_card _credit_card_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._credit_card
    ADD CONSTRAINT _credit_card_pkey PRIMARY KEY (id);


--
-- TOC entry 2984 (class 2606 OID 59802)
-- Name: _customer_type _customer_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._customer_type
    ADD CONSTRAINT _customer_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2986 (class 2606 OID 59810)
-- Name: _devolution _devolution_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._devolution
    ADD CONSTRAINT _devolution_pkey PRIMARY KEY (id);


--
-- TOC entry 2988 (class 2606 OID 59815)
-- Name: _document _document_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._document
    ADD CONSTRAINT _document_pkey PRIMARY KEY (id);


--
-- TOC entry 2990 (class 2606 OID 59823)
-- Name: _document_type _document_type_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._document_type
    ADD CONSTRAINT _document_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2992 (class 2606 OID 59831)
-- Name: _game _game_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game
    ADD CONSTRAINT _game_pkey PRIMARY KEY (id);


--
-- TOC entry 2994 (class 2606 OID 59842)
-- Name: _gender _gender_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._gender
    ADD CONSTRAINT _gender_pkey PRIMARY KEY (id);


--
-- TOC entry 2996 (class 2606 OID 59850)
-- Name: _item _item_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._item
    ADD CONSTRAINT _item_pkey PRIMARY KEY (id);


--
-- TOC entry 2998 (class 2606 OID 59855)
-- Name: _language _language_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._language
    ADD CONSTRAINT _language_pkey PRIMARY KEY (id);


--
-- TOC entry 3000 (class 2606 OID 59863)
-- Name: _order _order_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT _order_pkey PRIMARY KEY (id);


--
-- TOC entry 3002 (class 2606 OID 59874)
-- Name: _payment_method _payment_method_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._payment_method
    ADD CONSTRAINT _payment_method_pkey PRIMARY KEY (id);


--
-- TOC entry 3004 (class 2606 OID 59882)
-- Name: _person _person_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._person
    ADD CONSTRAINT _person_pkey PRIMARY KEY (id);


--
-- TOC entry 3008 (class 2606 OID 59887)
-- Name: _platform _platform_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._platform
    ADD CONSTRAINT _platform_pkey PRIMARY KEY (id);


--
-- TOC entry 3010 (class 2606 OID 59892)
-- Name: _publisher _publisher_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._publisher
    ADD CONSTRAINT _publisher_pkey PRIMARY KEY (id);


--
-- TOC entry 3012 (class 2606 OID 59900)
-- Name: _state _state_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._state
    ADD CONSTRAINT _state_pkey PRIMARY KEY (id);


--
-- TOC entry 3014 (class 2606 OID 59905)
-- Name: _status _status_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._status
    ADD CONSTRAINT _status_pkey PRIMARY KEY (id);


--
-- TOC entry 3016 (class 2606 OID 59910)
-- Name: _stock _stock_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._stock
    ADD CONSTRAINT _stock_pkey PRIMARY KEY (id);


--
-- TOC entry 3018 (class 2606 OID 59915)
-- Name: _tracking _tracking_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._tracking
    ADD CONSTRAINT _tracking_pkey PRIMARY KEY (id);


--
-- TOC entry 3020 (class 2606 OID 59920)
-- Name: _wallet _wallet_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._wallet
    ADD CONSTRAINT _wallet_pkey PRIMARY KEY (id);


--
-- TOC entry 3006 (class 2606 OID 59924)
-- Name: _person uk_oywvttjtq2mi04tkanptmuuie; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._person
    ADD CONSTRAINT uk_oywvttjtq2mi04tkanptmuuie UNIQUE (email);


--
-- TOC entry 2982 (class 2606 OID 59922)
-- Name: _credit_card uk_t7buhxhaf3y8wt9xi3emtn2ga; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._credit_card
    ADD CONSTRAINT uk_t7buhxhaf3y8wt9xi3emtn2ga UNIQUE (number);


--
-- TOC entry 3027 (class 2606 OID 59957)
-- Name: _credit_card fk15x8tycuiwp6f9gbxxh6y75mq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._credit_card
    ADD CONSTRAINT fk15x8tycuiwp6f9gbxxh6y75mq FOREIGN KEY (banner_id) REFERENCES public._banner(id);


--
-- TOC entry 3048 (class 2606 OID 60062)
-- Name: _order fk1s17kgbve6qulsujomrxfaq74; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fk1s17kgbve6qulsujomrxfaq74 FOREIGN KEY (tracking_id) REFERENCES public._tracking(id);


--
-- TOC entry 3037 (class 2606 OID 60007)
-- Name: _game_languages fk2nmgaoputu0p9qxj82uxnyn1i; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game_languages
    ADD CONSTRAINT fk2nmgaoputu0p9qxj82uxnyn1i FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3045 (class 2606 OID 60047)
-- Name: _order fk2pqcrovg2l1iyirjvfjvmgmbj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fk2pqcrovg2l1iyirjvfjvmgmbj FOREIGN KEY (coupon_id) REFERENCES public._coupon(id);


--
-- TOC entry 3040 (class 2606 OID 60022)
-- Name: _item fk3ei66dknf6hslyo0e7pmj7cci; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._item
    ADD CONSTRAINT fk3ei66dknf6hslyo0e7pmj7cci FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3041 (class 2606 OID 60027)
-- Name: _language_game fk3qlh13bokh6vxu10qmbmekkh4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._language_game
    ADD CONSTRAINT fk3qlh13bokh6vxu10qmbmekkh4 FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3035 (class 2606 OID 59997)
-- Name: _game_genders fk5jje8t3w0sp60watcfdkwv6e4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game_genders
    ADD CONSTRAINT fk5jje8t3w0sp60watcfdkwv6e4 FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3034 (class 2606 OID 59992)
-- Name: _game_genders fk5nmsptvrmeitbhmcpgqi206vt; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game_genders
    ADD CONSTRAINT fk5nmsptvrmeitbhmcpgqi206vt FOREIGN KEY (genders_id) REFERENCES public._gender(id);


--
-- TOC entry 3036 (class 2606 OID 60002)
-- Name: _game_languages fk662wjkrd1lq2cfj2ed7000y8a; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game_languages
    ADD CONSTRAINT fk662wjkrd1lq2cfj2ed7000y8a FOREIGN KEY (languages_id) REFERENCES public._language(id);


--
-- TOC entry 3043 (class 2606 OID 60037)
-- Name: _order fk6g7abmp5gvjnfy5xx1q2ocgqx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fk6g7abmp5gvjnfy5xx1q2ocgqx FOREIGN KEY (address_id) REFERENCES public._address(id);


--
-- TOC entry 3049 (class 2606 OID 60067)
-- Name: _order_item_list fk8ej7b9odg2djcg0ohesdual5c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order_item_list
    ADD CONSTRAINT fk8ej7b9odg2djcg0ohesdual5c FOREIGN KEY (item_list_id) REFERENCES public._item(id);


--
-- TOC entry 3044 (class 2606 OID 60042)
-- Name: _order fk92dogjpv2l6yyjq0ipwvhl7un; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fk92dogjpv2l6yyjq0ipwvhl7un FOREIGN KEY (address_billing_id) REFERENCES public._address(id);


--
-- TOC entry 3032 (class 2606 OID 59982)
-- Name: _game fk93vg9aiknsxl63rcgjt0xvkex; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game
    ADD CONSTRAINT fk93vg9aiknsxl63rcgjt0xvkex FOREIGN KEY (platform_id) REFERENCES public._platform(id);


--
-- TOC entry 3021 (class 2606 OID 59927)
-- Name: _address fka4by3p4gyob7m1qsedynul5s4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._address
    ADD CONSTRAINT fka4by3p4gyob7m1qsedynul5s4 FOREIGN KEY (address_type_id) REFERENCES public._address_type(id);


--
-- TOC entry 3022 (class 2606 OID 59932)
-- Name: _address fkb4rqqifvrfm8yhlmb5w79p8pf; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._address
    ADD CONSTRAINT fkb4rqqifvrfm8yhlmb5w79p8pf FOREIGN KEY (city_id) REFERENCES public._city(id);


--
-- TOC entry 3038 (class 2606 OID 60012)
-- Name: _gender_game fkbvejlb74tkihc07a25gqqccos; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._gender_game
    ADD CONSTRAINT fkbvejlb74tkihc07a25gqqccos FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3052 (class 2606 OID 60082)
-- Name: _order_payment_method_list fke10r226h90mm67qn0uq32jmy4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order_payment_method_list
    ADD CONSTRAINT fke10r226h90mm67qn0uq32jmy4 FOREIGN KEY (order_id) REFERENCES public._order(id);


--
-- TOC entry 3057 (class 2606 OID 60107)
-- Name: _stock fkew4ea7ax6dmdb5u6n0s72cdjl; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._stock
    ADD CONSTRAINT fkew4ea7ax6dmdb5u6n0s72cdjl FOREIGN KEY (game_id) REFERENCES public._game(id);


--
-- TOC entry 3029 (class 2606 OID 59967)
-- Name: _devolution fkfaev0yj1k1u3yoa4uqh928x5m; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._devolution
    ADD CONSTRAINT fkfaev0yj1k1u3yoa4uqh928x5m FOREIGN KEY (order_id) REFERENCES public._order(id);


--
-- TOC entry 3033 (class 2606 OID 59987)
-- Name: _game fkh4b7cgkgimxlebxpptww7bbxq; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._game
    ADD CONSTRAINT fkh4b7cgkgimxlebxpptww7bbxq FOREIGN KEY (publisher_id) REFERENCES public._publisher(id);


--
-- TOC entry 3023 (class 2606 OID 59937)
-- Name: _address fkklhlykjpy8vmlex34iibk1949; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._address
    ADD CONSTRAINT fkklhlykjpy8vmlex34iibk1949 FOREIGN KEY (person_id) REFERENCES public._person(id);


--
-- TOC entry 3050 (class 2606 OID 60072)
-- Name: _order_item_list fkkwniy8fc3krlj27t1rjrwaoc7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order_item_list
    ADD CONSTRAINT fkkwniy8fc3krlj27t1rjrwaoc7 FOREIGN KEY (order_id) REFERENCES public._order(id);


--
-- TOC entry 3051 (class 2606 OID 60077)
-- Name: _order_payment_method_list fkl2lh2ckk7lkc3icpfo1t7chyg; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order_payment_method_list
    ADD CONSTRAINT fkl2lh2ckk7lkc3icpfo1t7chyg FOREIGN KEY (payment_method_list_id) REFERENCES public._payment_method(id);


--
-- TOC entry 3056 (class 2606 OID 60102)
-- Name: _person fkli6elk5nj6f5j44hi6pseknt4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._person
    ADD CONSTRAINT fkli6elk5nj6f5j44hi6pseknt4 FOREIGN KEY (wallet_id) REFERENCES public._wallet(id);


--
-- TOC entry 3039 (class 2606 OID 60017)
-- Name: _gender_game fkmk5sn7qihr05b5mkxrrpl8nek; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._gender_game
    ADD CONSTRAINT fkmk5sn7qihr05b5mkxrrpl8nek FOREIGN KEY (gender_id) REFERENCES public._gender(id);


--
-- TOC entry 3046 (class 2606 OID 60052)
-- Name: _order fkni0yfvvbjld0yo6o50r9xic8q; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fkni0yfvvbjld0yo6o50r9xic8q FOREIGN KEY (customer_id) REFERENCES public._person(id);


--
-- TOC entry 3042 (class 2606 OID 60032)
-- Name: _language_game fknujmxx7uwju6smyoffe9cawlm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._language_game
    ADD CONSTRAINT fknujmxx7uwju6smyoffe9cawlm FOREIGN KEY (language_id) REFERENCES public._language(id);


--
-- TOC entry 3025 (class 2606 OID 59947)
-- Name: _cart_item_list fkok167mpvud3parpx1h5n4qb6n; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._cart_item_list
    ADD CONSTRAINT fkok167mpvud3parpx1h5n4qb6n FOREIGN KEY (cart_id) REFERENCES public._cart(id);


--
-- TOC entry 3055 (class 2606 OID 60097)
-- Name: _person fkq0urr8j6xrrn9pm2u28qu07xo; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._person
    ADD CONSTRAINT fkq0urr8j6xrrn9pm2u28qu07xo FOREIGN KEY (customer_type_id) REFERENCES public._customer_type(id);


--
-- TOC entry 3028 (class 2606 OID 59962)
-- Name: _credit_card fkqfso4vsnk6f10l1ekb61r4wbs; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._credit_card
    ADD CONSTRAINT fkqfso4vsnk6f10l1ekb61r4wbs FOREIGN KEY (customer_id) REFERENCES public._person(id);


--
-- TOC entry 3026 (class 2606 OID 59952)
-- Name: _city fkqipc9b53idu54sor8vor3nsca; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._city
    ADD CONSTRAINT fkqipc9b53idu54sor8vor3nsca FOREIGN KEY (state_id) REFERENCES public._state(id);


--
-- TOC entry 3054 (class 2606 OID 60092)
-- Name: _person fkr6hgprfexxs9oryamkc3lm2d6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._person
    ADD CONSTRAINT fkr6hgprfexxs9oryamkc3lm2d6 FOREIGN KEY (cart_id) REFERENCES public._cart(id);


--
-- TOC entry 3031 (class 2606 OID 59977)
-- Name: _document fkrai11mutxhnbdniaeucnnu266; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._document
    ADD CONSTRAINT fkrai11mutxhnbdniaeucnnu266 FOREIGN KEY (person_id) REFERENCES public._person(id);


--
-- TOC entry 3047 (class 2606 OID 60057)
-- Name: _order fkrh1mmpwoo45iiki3hs30e1u4q; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._order
    ADD CONSTRAINT fkrh1mmpwoo45iiki3hs30e1u4q FOREIGN KEY (status_id) REFERENCES public._status(id);


--
-- TOC entry 3053 (class 2606 OID 60087)
-- Name: _payment_method fkrhd9atc2fa6b7gvpoibl36jsw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._payment_method
    ADD CONSTRAINT fkrhd9atc2fa6b7gvpoibl36jsw FOREIGN KEY (credit_card_id) REFERENCES public._credit_card(id);


--
-- TOC entry 3024 (class 2606 OID 59942)
-- Name: _cart_item_list fkrmao9uh7om40u4a6ksy5r22be; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._cart_item_list
    ADD CONSTRAINT fkrmao9uh7om40u4a6ksy5r22be FOREIGN KEY (item_list_id) REFERENCES public._item(id);


--
-- TOC entry 3030 (class 2606 OID 59972)
-- Name: _document fktnmoerlxyu799vy7jwdeumfk9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public._document
    ADD CONSTRAINT fktnmoerlxyu799vy7jwdeumfk9 FOREIGN KEY (document_type_id) REFERENCES public._document_type(id);


-- Completed on 2021-06-11 22:46:03 -03

--
-- PostgreSQL database dump complete
--


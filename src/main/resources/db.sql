-- public.patient definition

-- Drop table

-- DROP TABLE public.patient;

CREATE TABLE public.patient (
	id int8 NOT NULL,
	full_name varchar(255) NOT NULL,
	gender varchar(255) NOT NULL,
	phone_number int8 NOT NULL,
	CONSTRAINT constraint_name UNIQUE (phone_number),
	CONSTRAINT patient_pkey PRIMARY KEY (id)
);
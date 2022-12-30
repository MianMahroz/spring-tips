CREATE TABLE IF NOT EXISTS orders(
                                    id serial  NOT NULL,
                                    product_id bigint NOT NULL,
                                    qty integer NOT NULL,
                                    PRIMARY KEY (id)
);

INSERT
INTO orders (product_id,qty)
VALUES (299,2)
ON CONFLICT do nothing;

INSERT
INTO orders (product_id, qty)
VALUES (100,3)
ON CONFLICT do nothing;
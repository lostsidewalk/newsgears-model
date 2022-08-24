
drop table if exists staging_posts cascade;

drop table if exists published_posts cascade;

create table published_posts (
    id bigserial not null primary key,
    post_title varchar(512) not null,
    importer_id smallint not null,
    publish_timestamp timestamp with time zone
);

create table staging_posts (
    id bigserial not null primary key,
    post_title varchar(512) not null,
    importer_id varchar(256) not null,
    object_source json not null,
    import_timestamp timestamp with time zone,
    is_published boolean not null default false,
    post_hash varchar(64) not null,
    published_post_id bigint,

    constraint fk_published_post_id foreign key(published_post_id) references published_posts(id)
);

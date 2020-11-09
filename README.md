# WIP: configurations-backend
Backend for the "configurations" service.

The configurations service provides possibility to create sets of different configurations (e.g. like IDE settings).
The `metadata` part presents the template of the properties (e.g. type, group).
The `instance` part are the values for the metadata. One metadata configuration can have many `instances`.

Tech stack: Jakarta EE 8 + JPA (Hibernate) + MySQL + Docker + Wildfly bootable jar

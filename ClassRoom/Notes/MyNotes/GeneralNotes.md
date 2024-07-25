Side note for labs:
In some cases when you start the application the databases due to relations doesn't drop then you get an error, so you need to run this code in the database to clean it:

```sql
DROP SCHEMA PUBLIC CASCADE
```

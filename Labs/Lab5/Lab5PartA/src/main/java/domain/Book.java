package domain;

import javax.persistence.*;

@Entity
public class Book extends Product
{
    String isbn;
}
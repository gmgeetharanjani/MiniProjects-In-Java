package com.example.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel {
    private int amount;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private PaymentProvider paymentProvider;
    private int referenceNumber;
    private Date timeStamp;
}

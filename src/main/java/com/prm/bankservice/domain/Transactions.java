package com.prm.bankservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Transactions {
    @Id
    @SequenceGenerator(
            name="tran_sequence",
            sequenceName = "tran_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "tran_sequence")
    private int     tranSeq;
    private Date    tranDate;
    private String  tranOp;
    private long    tranAmnt;
    private long    tranCust;


}
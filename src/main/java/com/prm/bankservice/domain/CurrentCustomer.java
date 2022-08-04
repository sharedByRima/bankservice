package com.prm.bankservice.domain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CurrentCustomer {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "user_sequence")
    private int         CurrUserSeq ;
    @Column(nullable = true)
    private int         CurrUserCrdId;
    @Column(nullable = true)
    private int         CurrUserId;
    private String      CurrUserSecPinCode;
    private String      CurrUserSecCode;
}


/*
package com.prm.bankservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class CurrentUser {

    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "user_sequence")
    private int         CurrUserSeq ;
    @Column(nullable = true)
    private int         CurrUserCrdId;
    private String      CurrUserSecPinCode;
    private String      CurrUserSecCode;
}

 */
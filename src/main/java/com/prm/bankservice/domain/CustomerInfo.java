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
    public class CustomerInfo {
        @Id
        @SequenceGenerator(
                name="cust_sequence",
                sequenceName = "cust_sequence",
                allocationSize = 1
        )
        @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "cust_sequence")
        private int         custSeq ;
        private String      custName;
        private String      custFamily;
        private String      custSocID;
        private int         custCrdId;
        private String      custCrdAccount;
        private String      custCrdDate;
        private Long         custAmount;
        private String      custSecPinCode;
        private String      custSecCode;
        private int         custBnkCode;






    }

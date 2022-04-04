package _01_gringotts_database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name = "wizards_deposits")
public class WizardDeposits {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY) // Primary Key
    private int id;

    @Column (name = "first_name", length = 50) // Може да има NULL
    private String firstName;

    @Column (name = "last_name", length = 60, nullable = false) // не може да има NULL стойност
    private String lastName;

    @Column(length = 1000)
    private String notes;


    private int age;

    @Column (name = "magic_wand_creator", length = 100)
    private String magicWandCreator;

    @Column (name = "magic_wand_size")
    private short magicWandSize;

    @Column (name = "deposit_group", length = 20)
    private String depositGroup;

    @Column (name = "deposit_start_date", nullable = false)
    private LocalDateTime depositStartDate;

    @Column (name = "deposit_amount", nullable = false)
    private BigDecimal depositAmount;

    @Column (name = "deposit_interest", nullable = false)
    private BigDecimal getDepositInterest;

    @Column (name = "deposit_charge", nullable = false)
    private BigDecimal depositCharge;

    @Column (name = "deposit_expiration_date", nullable = false)
    private LocalDateTime depositExpirationDate;

    @Column (name = "is_deposit_expired" )
    private boolean depositExpired;

    public WizardDeposits () {} // за find операциите е задължително
















}

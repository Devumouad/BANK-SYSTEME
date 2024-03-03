# BANK-SYSTEME
JAVA APPLICATION FOR BANK MANAGEMENT SYSTEME


this systeme handles sign up and sing up to the bank out managment systme every user can register the client who wants to open a bank account 
it also has an interface for sending request and all of the things that makes it easy for the user experience
# banksystem Database README

## Introduction
This README provides information about the tables in the banksystem database.

## Tables Overview
- **users**: Contains information about users.
- **usersemp**: Contains information about employees.
- **userbalance**: Contains user balances.
- **moneysent**: Records money sent transactions.
- **moneyreceived**: Records money received transactions.
- **additionalinfo**: Additional information table.

## Table Details
| Table Name      | Rows | Type   | Collation         | Size  | Overhead |
|-----------------|------|--------|-------------------|-------|----------|
| additionalinfo  | 3    | InnoDB | latin1_swedish_ci | 32 KiB| -        |
| moneyreceived   | 5    | InnoDB | latin1_swedish_ci | 32 KiB| -        |
| moneysent       | 10   | InnoDB | latin1_swedish_ci | 32 KiB| -        |
| userbalance     | 1    | InnoDB | latin1_swedish_ci | 16 KiB| -        |
| users           | 7    | InnoDB | latin1_swedish_ci | 16 KiB| -        |
| usersemp        | 4    | InnoDB | latin1_swedish_ci | 32 KiB| -        |

## Summary
- **Total Tables**: 6
- **Total Rows**: 30
- **Type**: InnoDB
- **Collation**: latin1_swedish_ci
- **Total Size**: 160 KiB
- **Total Overhead**: 0 B

## Conclusion
This README provides an overview of the tables in the banksystem database. For further details, refer to the individual table descriptions.


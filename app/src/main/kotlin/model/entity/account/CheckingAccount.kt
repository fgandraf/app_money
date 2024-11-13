package model.entity.account

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import model.entity.Group
import model.enums.AccountType

@Entity
@DiscriminatorValue("CHECKING")
class CheckingAccount(
    name: String,
    description: String = "",
    position: Int,
    icon: String,
    balance: Double,
    group: Group,
    @Column(name = "open_balance")
    var openBalance: Double = 0.0,

    @Column(name = "overdraft_limit")
    var overdraftLimit: Double = 0.0,
    ) : BankAccount(
    type = AccountType.CHECKING,
    name = name,
    description = description,
    position = position,
    icon = icon,
    balance = balance,
    group = group
    ) {

    constructor(
        id: Long,
        name: String,
        description: String = "",
        position: Int,
        icon: String,
        balance: Double,
        group: Group,
        openBalance: Double = 0.0,
        overdraftLimit: Double = 0.0
    ) : this(
        name = name,
        description = description,
        position = position,
        icon = icon,
        balance = balance,
        group = group,
        openBalance = openBalance,
        overdraftLimit = overdraftLimit
    ) {
        this.id = id
    }
}
export type DenominationEntry = {
    denomination: number
    count: number
}

export type DenominationDifferenceEntry = {
    denomination: number
    differenceCount: number
}

export type DenominationResponse = {
    denominations: DenominationEntry[]
    differences: DenominationDifferenceEntry[]
}
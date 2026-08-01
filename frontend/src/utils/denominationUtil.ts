import type {DenominationDifferenceEntry, DenominationEntry} from '../types/denomination'

const denominations = [20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1]

/**
 * Calculates the required denominations for the provided amount
 * @param amount
 */
export function calculateDenominations(amount: number): DenominationEntry[] {
    const result: DenominationEntry[] = [];
    amount = Math.round(amount * 100);

    for (const denomination of denominations.toSorted((a, b) => b - a)) {
        const count = Math.floor(amount / denomination);
        result.push({denomination: (denomination / 100), count});
        amount %= denomination;
    }
    return result;
}

/**
 * Calculates the difference in used denominations in between the provided lists
 * @param currentAmountList
 * @param previousAmountList
 */
export function calculateDenominationDifference(currentAmountList: DenominationEntry[], previousAmountList: DenominationEntry[]): DenominationDifferenceEntry[] {
    const differences: DenominationDifferenceEntry[] = [];
    for (let index = 0; index < denominations.length; index++) {
        if (currentAmountList[index].count > 0 || previousAmountList[index].count > 0) {
            const differenceCount = currentAmountList[index].count - previousAmountList[index].count;
            differences.push({denomination: currentAmountList[index].denomination, differenceCount});
        }

    }
    return differences;
}
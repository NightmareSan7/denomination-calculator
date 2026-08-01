import type {DenominationResponse} from '../types/denomination'

/**
 * Sends a POST request to the backend to calculate the denomination for the provided amounts
 * @param currentAmount
 * @param previousAmount
 */
export async function handleDenominationRequest(currentAmount: number, previousAmount: number | null): Promise<DenominationResponse> {
    const response = await fetch('/api/tooling/denomination/v1', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({currentAmount, previousAmount})
    });

    if (!response.ok) {
        throw new Error('Die Berechnung der Stückelung ist fehlgeschlagen');
    }
    return response.json();
}
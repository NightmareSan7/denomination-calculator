import type {DenominationResponse} from '../types/denomination'
import type {ErrorResponse} from '../types/ErrorResponse'

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
        let errorMessage = `Die Berechnung der Stückelung ist fehlgeschlagen: ${response.status} - ${response.statusText}`;
        try {
            const errorResponse: ErrorResponse = await response.json();
            errorMessage = `${errorResponse.error} - ${errorResponse.errorDetails}`
        } catch { /* empty */
        }
        throw new Error(errorMessage);
    }
    return response.json();
}
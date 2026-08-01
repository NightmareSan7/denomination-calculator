const euroFormatter = new Intl.NumberFormat('de-DE', {
    style: 'currency',
    currency: 'EUR',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
});

/**
 * Formats the provided value to German number format in €
 * @param value
 */
export function formatEuro(value: number): string {
    return euroFormatter.format(value);
}
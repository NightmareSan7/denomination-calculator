export type ErrorResponse = {
    errorTag: string,
    success: boolean,
    error: string,
    validationError: boolean,
    status: number,
    errorDetails: string
}
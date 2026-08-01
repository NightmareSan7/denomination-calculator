import {useState} from 'react'
import './App.css'
import NumberField from './components/NumberField';
import {handleDenominationRequest} from './services/denominationService'
import {calculateDenominationDifference, calculateDenominations} from './utils/denominationUtil';
import type {DenominationDifferenceEntry, DenominationEntry} from './types/denomination';
import Switch from '@mui/material/Switch';
import FormControlLabel from '@mui/material/FormControlLabel';
import Grid from '@mui/material/Grid';
import DenominationTable from './components/DenominationTable';
import {formatEuro} from './utils/currencyUtil.ts';


function App() {
    const [calculateInBackend, setCalculateInBackend] = useState(true);
    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setCalculateInBackend(event.target.checked);
    };
    const [rows, setRows] = useState<DenominationEntry[]>([])
    const [diffRows, setDiffRows] = useState<DenominationDifferenceEntry[]>([])
    const [inputAmount, setInputAmount] = useState<number | null>(null)
    const [currentCalculationAmount, setCurrentCalculationAmount] = useState<number | null>(null)
    const [previousCalculationAmount, setPreviousCalculationAmount] = useState<number | null>(null)

    /**
     * Sends a request for calculation to the backend
     */
    async function handleBackendRequest() {
        if (!inputAmount) return;
        try {
            const response = await handleDenominationRequest(inputAmount, currentCalculationAmount)
            setPreviousCalculationAmount(currentCalculationAmount)
            setCurrentCalculationAmount(inputAmount)
            setRows(response.denominations)
            setDiffRows(response.differences)
        } catch (error) {
            alert(error instanceof Error ? error.message : 'Ein Unbekannter Fehler ist aufgetreten');
        }

    }

    /**
     * Processes the request within the frontend
     */
    function processRequest() {
        if (!inputAmount) return;
        let currentDenominations = calculateDenominations(inputAmount);

        if (currentCalculationAmount) {
            const previousDenominations = calculateDenominations(currentCalculationAmount);
            const differences: DenominationDifferenceEntry[] = calculateDenominationDifference(currentDenominations, previousDenominations);
            setDiffRows(differences);
            setPreviousCalculationAmount(currentCalculationAmount)
        }
        currentDenominations = currentDenominations.filter(entry => entry.count > 0);
        setRows(currentDenominations);
        setCurrentCalculationAmount(inputAmount)
    }

    return (
        <>
            <div className='denomination-area'>
                <h1>Stückelungsrechner</h1>
                <FormControlLabel
                    control={<Switch
                        checked={calculateInBackend}
                        onChange={handleChange}/>}
                    label='im Backend berechnen'
                />
                <div className='amount-input'>
                    <NumberField
                        label='Betrag'
                        min={0.01}
                        step={0.01}
                        locale='de-DE'
                        size='small'
                        format={{
                            minimumFractionDigits: 0,
                            maximumFractionDigits: 2,
                        }}
                        onValueChange={(value) => setInputAmount(value)}/>
                </div>
                <button className='calculate-button'
                        type='button'
                        disabled={(!inputAmount)}
                        onClick={() =>
                            calculateInBackend
                                ? handleBackendRequest()
                                : processRequest()
                        }
                >
                    Berechnen
                </button>

                {currentCalculationAmount && (
                    <Grid container spacing={2}
                          sx={{
                              width: 'max-content',
                              maxWidth: '100%',
                          }}>
                        <Grid size='auto'>
                            <DenominationTable
                                title={`Aktuelle Stückelung: ${formatEuro(currentCalculationAmount)}`}
                                valueHeader='Anzahl'
                                rows={
                                    rows.map(row => ({
                                        denomination: row.denomination,
                                        value: row.count
                                    }))
                                }/>
                        </Grid>

                        {previousCalculationAmount && (
                            <Grid size='auto'>
                                <DenominationTable
                                    title={`Differenz zu: ${formatEuro(previousCalculationAmount)}`}
                                    valueHeader='Differenz'
                                    rows={diffRows.map((row) => ({
                                        denomination: row.denomination,
                                        value: row.differenceCount > 0 ? `+${row.differenceCount}` : row.differenceCount,
                                    }))}
                                />
                            </Grid>
                        )}
                    </Grid>)}
            </div>
        </>
    )
}

export default App

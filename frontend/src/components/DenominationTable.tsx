type DenominationTableProps = {
    title: string;
    valueHeader: string;
    rows: {
        denomination: number;
        value: number | string;
    }[];
};

export default function DenominationTable({
                                              title,
                                              valueHeader,
                                              rows
                                          }: DenominationTableProps) {
    return (
        <div>
            <h3>{title}</h3>
            <table className='denomination-table'>
                <thead>
                <tr>
                    <th>Stückelung in €</th>
                    <th>{valueHeader}</th>
                </tr>
                </thead>

                <tbody>
                {rows.map((row) => (
                    <tr key={row.denomination}>
                        <td>{row.denomination.toLocaleString('de-DE')}</td>
                        <td>{row.value}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}
import { useState } from 'react';

const LastTransactions = () => {
    // Aqui você pode adicionar lógica para buscar as últimas transações
    const [transactions] = useState([
        { id: 1, description: 'Compra no mercado', amount: -50.0 },
        { id: 2, description: 'Depósito', amount: 200.0 },
        { id: 3, description: 'Restaurante', amount: -30.0 },
    ]);

    // Função para editar uma transação
    const handleEditTransaction = (id: number) => {
        // Adicione aqui a lógica para editar a transação com o ID fornecido
        console.log(`Editando transação com ID: ${id}`);
    };

    return (
        <div className="bg-white shadow-md rounded-md p-4">
            <h2 className="text-xl font-semibold mb-4">Últimas Transações</h2>
            <table className="w-full">
                <thead>
                    <tr className="bg-gray-200">
                        <th className="py-2 px-4 text-left">Description</th>
                        <th className="py-2 px-4 text-left">Amount</th>
                        <th className="py-2 px-4 text-left">Options</th>
                    </tr>
                </thead>
                <tbody>
                    {transactions.map(transaction => (
                        <tr key={transaction.id} className="border-b border-gray-200">
                            <td className="py-2 px-4">{transaction.description}</td>
                            <td className="py-2 px-4">R$ {transaction.amount.toFixed(2)}</td>
                            <td className="py-2 px-4">
                                <button className="flex items-center text-blue-500 hover:text-blue-700" onClick={() => handleEditTransaction(transaction.id)}>
                                    Edit
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default LastTransactions;

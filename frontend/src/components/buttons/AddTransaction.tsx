// Componente do botão de adicionar cartões novos (AddCard.tsx)
import React from 'react';

interface AddTransactionProps {
    onClick: () => void;
}

const AddTransaction: React.FC<AddTransactionProps> = ({ onClick }) => {
    return (
        <button className="bg-green-500 max-w-25 max-h-30 rounded-md p-2 m-2 font-semibold text-white" onClick={onClick}>
            Add transaction
        </button>
    );
}

export default AddTransaction;

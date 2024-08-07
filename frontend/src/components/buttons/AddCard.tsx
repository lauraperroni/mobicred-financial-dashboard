// Componente do botão de adicionar cartões novos (AddCard.tsx)
import React from 'react';

interface AddCardProps {
    onClick: () => void;
}

const AddCard: React.FC<AddCardProps> = ({ onClick }) => {
    return (
        <button className="bg-green-500 max-w-25 max-h-30 rounded-md p-2 m-2 font-semibold text-white" onClick={onClick}>
            Add new account
        </button>
    );
}

export default AddCard;

// Em BankCard.tsx

import React, { useState } from 'react';
import DeleteCard from '../buttons/DeleteCard';
import DetailsCard from '../buttons/DetailsCard';
import BankCardDetailsModal from './BankCardDetails'; // Importando o componente de modal

interface BankCardProps {
    bankName: string;
    currentBalance: number;
    nextBillingDate: string;
    billingBalance: number;
    onDelete: () => void; // Propriedade onDelete como uma função sem argumentos e retorno
    onOpenModal: () => void; // Adicionando a propriedade onOpenModal
}

function BankCard({ bankName, currentBalance, nextBillingDate, billingBalance, onDelete, onOpenModal }: BankCardProps) {
    const [isModalOpen, setIsModalOpen] = useState(false); // Estado para controlar se o modal está aberto

    // Função para abrir o modal
    const handleOpenModalBankCard = () => {
        setIsModalOpen(true);
    };

    // Função para fechar o modal
    const handleCloseModalBankCard = () => {
        setIsModalOpen(false);
    };

    return (
        <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-80 w-72 m-4">
            <div className="bg-green-500 text-white px-4 py-2 p-8">
                <h2 className="text-xl font-semibold">Bank {bankName}</h2>
            </div>
            <div className="p-4">
                <div className="mb-2">
                    <span className="font-semibold text-gray-800">Balance:</span> $ {currentBalance.toFixed(2)}
                </div>
                <div className="mb-2">
                    <span className="font-semibold text-gray-800">Due date:</span> {nextBillingDate}
                </div>
                <div>
                    <span className="font-semibold text-gray-800">Amount:</span> $ {billingBalance.toFixed(2)}
                </div>
                <div className="flex justify-between">
                    <DeleteCard onDelete={onDelete} /> {/* Passando a função onDelete para o componente DeleteCard */}
                    <DetailsCard onOpenModal={onOpenModal} bankName={bankName} /> {/* Passando a função para abrir o modal e o nome do banco para o componente DetailsCard */}
                </div>
            </div>
            {/* Renderizando o modal apenas se isModalOpen for verdadeiro */}
            <BankCardDetailsModal isOpen={isModalOpen} onCloseModal={handleCloseModalBankCard} bankName={bankName} currentBalance={currentBalance} nextBillingDate={nextBillingDate} billingBalance={billingBalance} />
        </div>
    );
}

export default BankCard;

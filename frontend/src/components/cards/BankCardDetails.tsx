// Em BankCardDetails.tsx

import React from 'react';

interface BankCardDetailsProps {
    isOpen: boolean; // Estado que controla se o modal está aberto ou fechado
    onCloseModal: () => void; // Função para fechar o modal
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
}

const BankCardDetails: React.FC<BankCardDetailsProps> = ({ isOpen, onCloseModal, bankName, balance, nextBillingDate, billingBalance }) => {
    if (!isOpen) return null;

    return (
        <div className="fixed top-0 left-0 flex justify-center items-center w-full h-full bg-black bg-opacity-50">
            <div className="bg-gray-50 shadow-lg rounded-lg overflow-hidden max-w-100 w-72 m-4">
                <div className="bg-green-500 text-white px-4 py-2 p-8">
                    <h2 className="text-xl font-semibold">Bank {bankName} Details</h2>
                </div>
                <div className="p-4">
                    <p className="mb-2"><span className="font-semibold">Balance:</span> $ {balance.toFixed(2)}</p>
                    <p className="mb-2"><span className="font-semibold">Due date:</span> {nextBillingDate}</p>
                    <p><span className="font-semibold">Amount:</span> $ {billingBalance.toFixed(2)}</p>
                    <button onClick={onCloseModal} className="bg-gray-200 px-4 py-2 mt-4 rounded-md">Close</button>
                </div>
            </div>
        </div>
    );
};

export default BankCardDetails;

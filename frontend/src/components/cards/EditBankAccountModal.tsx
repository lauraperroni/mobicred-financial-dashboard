import React, { useState, useEffect } from 'react';
import { putBankAccounts } from '../../services/Bank Accounts/BankAccountsService';

interface EditBankAccountModalProps {
    isOpen: boolean;
    onCloseModal: () => void;
    bankNumber: number;
    bankName: string;
    balance: number;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    id: number;
    onSaveChanges: (formData: any) => void; // Função para salvar alterações
}

const EditBankAccountModal: React.FC<EditBankAccountModalProps> = ({
    isOpen,
    onCloseModal,
    bankNumber,
    bankName,
    balance,
    nextBillingDate,
    billingBalance,
    accountType,
    id,
    onSaveChanges
}) => {
    const [formData, setFormData] = useState({
        bankName: '',
        bankNumber: '',
        balance: '',
        nextBillingDate: '',
        billingBalance: '',
        accountType: ''
    });

    // Atualiza o estado do formulário com os dados da conta bancária
    useEffect(() => {
        setFormData({
            bankName,
            bankNumber: bankNumber.toString(),
            balance: balance.toString(),
            nextBillingDate,
            billingBalance: billingBalance.toString(),
            accountType
        });
    }, [isOpen, bankNumber, bankName, balance, nextBillingDate, billingBalance, accountType]);

    // Função para lidar com mudanças nos inputs do formulário
    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value // Atualiza o campo específico com o novo valor
        }));
    };

    // Função para salvar as alterações
    const handleSaveChanges = async () => {
        try {
            // Cria uma cópia do formData atualizado para enviar na requisição
            const updatedData = {
                bankName: formData.bankName,
                bankNumber: parseInt(formData.bankNumber, 10),
                balance: parseFloat(formData.balance),
                nextBillingDate: formData.nextBillingDate,
                billingBalance: parseFloat(formData.billingBalance),
                accountType: formData.accountType,
                id // Garante que o ID seja incluído no objeto
            };

            console.log('edit id:', id);
            console.log('edit data: ', updatedData);
            await putBankAccounts(id, updatedData); // Chama o serviço para atualizar os dados

            onSaveChanges(updatedData); // Atualiza o estado pai com os dados atualizados
            onCloseModal(); // Fecha o modal após salvar
        } catch (error) {
            console.error('Erro ao atualizar conta bancária:', error);
        }
    };

    if (!isOpen) return null;

    return (
        <div className="fixed top-0 left-0 flex justify-center items-center w-full h-full bg-black bg-opacity-50">
            <div className="bg-white shadow-lg rounded-lg overflow-hidden max-w-lg w-full m-4">
                <div className="bg-green-500 text-white px-4 py-2">
                    <h2 className="text-xl font-semibold">Edit Bank Account Details</h2>
                </div>
                <div className="p-4 flex flex-wrap">
                    <div className="w-full md:w-1/2 pr-2">
                        <label className="block mb-2">Bank Name</label>
                        <input type="text" name="bankName" value={formData.bankName} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Bank Number</label>
                        <input type="text" name="bankNumber" value={formData.bankNumber} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Balance</label>
                        <input type="number" name="balance" value={formData.balance} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />
                    </div>
                    <div className="w-full md:w-1/2 pl-2">
                        <label className="block mb-2">Due Date</label>
                        <input type="text" name="nextBillingDate" value={formData.nextBillingDate} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Amount</label>
                        <input type="number" name="billingBalance" value={formData.billingBalance} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />

                        <label className="block mb-2">Account Type</label>
                        <input type="text" name="accountType" value={formData.accountType} onChange={handleInputChange} className="w-full px-3 py-2 mb-2 border rounded-md" />
                    </div>
                    <div className="w-full mt-4">
                        <button onClick={onCloseModal} className="bg-gray-200 px-4 py-2 rounded-md">Close</button>
                        <button onClick={handleSaveChanges} className="bg-green-500 text-white px-4 py-2 rounded-md ml-2">Save</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditBankAccountModal;

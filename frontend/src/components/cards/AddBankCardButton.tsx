import React, { useState } from 'react';
import AddBankCardModal from './AddBankCardModal';
import { BankAccountsService } from '../../services/Bank Accounts/BankAccountsService';

const AddBankCardButton: React.FC = () => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formData, setFormData] = useState({
        bankName: '',
        balance: '',
        nextBillingDate: '',
        billingBalance: '',
        accountType: ''
    });

    const handleOpenModal = () => {
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleAddCard = async (formData: any) => {
        try {
            // Remove o campo id antes de enviar a requisição
            const { id, ...dataToSend } = formData;
            await BankAccountsService.postBankAccounts(dataToSend);
            console.log('Conta bancária adicionada com sucesso');
        } catch (error) {
            console.error('Erro ao adicionar nova conta bancária:', error);
        }
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        handleAddCard(formData);
        setFormData({
            bankName: '',
            balance: '',
            nextBillingDate: '',
            billingBalance: '',
            accountType: ''
        });
        handleCloseModal();
    };

    return (
        <>
            <button onClick={handleOpenModal}>Add Bank Card</button>
            <AddBankCardModal isOpen={isModalOpen} onClose={handleCloseModal} onAddCard={handleSubmit} formData={formData} handleInputChange={handleInputChange} />
        </>
    );
};

export default AddBankCardButton;

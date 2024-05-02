// AddBankCardButton.tsx
import React, { useState } from 'react';
import AddBankCardModal from './AddBankCardModal';

interface AddBankCardButtonProps {
    onAddCard: (formData: any) => void;
}

const AddBankCardButton: React.FC<AddBankCardButtonProps> = ({ onAddCard }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [formData, setFormData] = useState({
        bankName: '',
        currentBalance: '',
        nextBillingDate: '',
        billingBalance: ''
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

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        onAddCard(formData);
        setFormData({
            bankName: '',
            currentBalance: '',
            nextBillingDate: '',
            billingBalance: ''
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

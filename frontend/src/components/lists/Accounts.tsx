import React, { useState } from 'react';
import BankCard from '../cards/BankCard';
import AddCard from '../buttons/AddCard';
import BankCardDetailsModal from '../cards/BankCardDetails';
import AddBankCardModal from '../cards/AddBankCardModal';
import TransactionListNoEdit from './TransactionListNoEdit';

const Accounts: React.FC = () => {
    const [cards, setCards] = useState([
        { bankName: 'Inter', currentBalance: 200, nextBillingDate: '04/05', billingBalance: 159.65 },
        { bankName: 'Nubank', currentBalance: 1000, nextBillingDate: '06/05', billingBalance: 2000.65 },
        { bankName: 'Santander', currentBalance: 1000, nextBillingDate: '06/05', billingBalance: 2000.65 }
    ]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<any>(null);
    const [formData, setFormData] = useState({
        bankName: '',
        currentBalance: '',
        nextBillingDate: '',
        billingBalance: ''
    });

    const handleOpenAddModal = () => {
        setIsAddModalOpen(true);
    };

    const handleCloseAddModal = () => {
        setIsAddModalOpen(false);
    };

    const handleAddCard = (formData: any) => {
        const newCard = {
            ...formData,
            currentBalance: parseFloat(formData.currentBalance),
            billingBalance: parseFloat(formData.billingBalance)
        };

        setCards(prevCards => [...prevCards, newCard]);
        handleCloseAddModal();
    };

    const handleDeleteCard = (index: number) => {
        const updatedCards = [...cards];
        updatedCards.splice(index, 1);
        setCards(updatedCards);
    };

    const handleOpenModalBankCard = (card: any) => {
        setSelectedBankCard(card);
    };

    const handleCloseModalBankCard = () => {
        setSelectedBankCard(null);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    return (
        <>
            <div className="flex bg-gray-50 justify-between">
                <div className="">
                    {/* Aqui você pode colocar o título, cumprimentos, etc */}
                </div>
                <div className="flex justify-left align-left m-8">
                    <AddCard onClick={handleOpenAddModal} />
                </div>
            </div>

            <div className='flex justify-center bg-gray-50'>
                {cards.map((card, index) => (
                    <BankCard
                        key={index}
                        bankName={card.bankName}
                        currentBalance={card.currentBalance}
                        nextBillingDate={card.nextBillingDate}
                        billingBalance={card.billingBalance}
                        onDelete={() => handleDeleteCard(index)}
                        onOpenModal={() => handleOpenModalBankCard(card)}
                    />
                ))}
            </div>
            <TransactionListNoEdit period={''} />

            {/* Modal de adição de cartão */}
            <AddBankCardModal isOpen={isAddModalOpen} onClose={handleCloseAddModal} onAddCard={handleAddCard} formData={formData} handleInputChange={handleInputChange} />

            {/* Modal de detalhes do cartão */}
            {selectedBankCard && (
                <BankCardDetailsModal
                    isOpen={true} // Corrigir para isOpen={selectedBankCard !== null}
                    onCloseModal={handleCloseModalBankCard}
                    bankName={selectedBankCard.bankName}
                    currentBalance={selectedBankCard.currentBalance}
                    nextBillingDate={selectedBankCard.nextBillingDate}
                    billingBalance={selectedBankCard.billingBalance}
                />
            )}

        </>
    );
};

export default Accounts;

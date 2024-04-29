import React, { useState } from 'react';
import BankCard from './cards/BankCard';
import HelloBankAccounts from './titles/HelloBankAccounts';
import AddCard from './buttons/AddCard';
import TransactionList from './TransactionList';
import LastTransactions from './LastTransactions';
import BankCardDetailsModal from './cards/BankCardDetails'; // Importando o componente de modal

const Accounts: React.FC = () => {
    const [cards, setCards] = useState([
        { bankName: 'Inter', currentBalance: 200, nextBillingDate: '04/05', billingBalance: 159.65 },
        { bankName: 'Nubank', currentBalance: 1000, nextBillingDate: '06/05', billingBalance: 2000.65 },
        { bankName: 'Santander', currentBalance: 1000, nextBillingDate: '06/05', billingBalance: 2000.65 }
    ]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<any>(null); // Aqui, definimos o estado para manter as informações do cartão selecionado

    const handleDeleteCard = (index: number) => {
        const updatedCards = [...cards];
        updatedCards.splice(index, 1);
        setCards(updatedCards);
    };

    const handleOpenModalBankCard = (card: any) => {
        setSelectedBankCard(card); // Quando o usuário clica em "Details", definimos o cartão selecionado
        setIsModalOpen(true);
    };

    const handleCloseModalBankCard = () => {
        setIsModalOpen(false);
    };

    return (
        <>
            <div className="flex bg-gray-50 justify-between">
                <div className="">
                    <HelloBankAccounts userName={'Laura'} />
                </div>
                <div className="flex justify-left align-left m-8">
                    <AddCard />
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
                        onOpenModal={() => handleOpenModalBankCard(card)} // Passamos a função handleOpenModalBankCard para o componente BankCard
                    />
                ))}
            </div>
            <TransactionList period={''} />
            {/* Renderizando o modal apenas se isModalOpen for verdadeiro */}
            {isModalOpen && selectedBankCard && (
                <BankCardDetailsModal
                    isOpen={isModalOpen}
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

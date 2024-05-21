import React, { useEffect, useState } from 'react';
import BankCard from '../cards/BankCard';
import AddCard from '../buttons/AddCard';
import BankCardDetailsModal from '../cards/BankCardDetails';
import AddBankCardModal from '../cards/AddBankCardModal';
import TransactionListNoEdit from './TransactionListNoEdit';
import { BankAccountsService } from '../../services';


// {bankName: 'Inter', idDto: 1, accountTypeDto: 'Poupança', balanceD: 10}
// {id: 3, accountType: 'aaaaaaaaaaaaaaaaaaaa', balance: 10, bankName: 'aaaaaaaaaaaaa'}

// export interface PostBankAccountsForm {
//     balance: number
//     bankName: string
//     nextBillingDate: string
//     billingBalance: number
//     accountType: string
//     userId: number
// }

export interface BankAccountsCards {
    balance: number;
    bankName: string;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    id: number;
}


const Accounts: React.FC = () => {

    const [cards, setCards] = useState<BankAccountsCards[]>([]);

    useEffect(() => {
        async function getConta() {
            const response = await BankAccountsService.getBankAccounts();
            if (response && response.status === 200) {
                console.log('Dados recebidos da API:', response.data);
                setCards(response.data); // Atualiza o estado com os dados recebidos da API
            } else {
                console.error('Erro ao buscar contas:', response);
            }
        }
        getConta();
    }, []);


    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<any>(null);

    const [formData, setFormData] = useState({
        bankName: '',
        balance: '',
        nextBillingDate: '',
        billingBalance: '',
        accountType: '',
        id: ''
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
            balance: parseFloat(formData.balance),
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
                        balance={card.balance !== null ? card.balance.toFixed(2) : ''}
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
                    balance={selectedBankCard.balance}
                    nextBillingDate={selectedBankCard.nextBillingDate}
                    billingBalance={selectedBankCard.billingBalance}
                />
            )}

        </>
    );
};

export default Accounts;

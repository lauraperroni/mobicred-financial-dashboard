import React, { useEffect, useState } from 'react';
import BankCard from '../cards/BankCard';
import AddCard from '../buttons/AddCard';
import BankCardDetailsModal from '../cards/BankCardDetails';
import AddBankCardModal from '../cards/AddBankCardModal';
import TransactionListNoEdit from './TransactionListNoEdit';
import { BankAccountsService } from '../../services';

export interface BankAccountsCards {
    balance: number;
    bankName: string;
    nextBillingDate: string; // Atualizado para aceitar nulo
    billingBalance: number; // Atualizado para aceitar nulo
    accountType: string;
    id: number;
}

const Accounts: React.FC = () => {
    const [cards, setCards] = useState<BankAccountsCards[]>([]);

    useEffect(() => {
        async function fetchAccounts() {
            try {
                const response = await BankAccountsService.getBankAccounts();
                if (response && response.status === 200) {
                    console.log('Dados recebidos da API:', response.data);
                    const formattedData = response.data.map((item: any) => ({
                        ...item,
                        nextBillingDate: item.nextBillingDate || 'N/A', // Definido como 'N/A' se nulo
                        billingBalance: item.billingBalance ?? null // Mantém nulo se for nulo
                    }));
                    setCards(formattedData);
                } else {
                    console.error('Erro ao buscar contas:', response);
                }
            } catch (error) {
                console.error('Erro na requisição:', error);
            }
        }
        fetchAccounts();
    }, []);

    // Restante do código permanece o mesmo

    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<BankAccountsCards | null>(null);

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
        const newCard: BankAccountsCards = {
            ...formData,
            balance: parseFloat(formData.balance),
            billingBalance: formData.billingBalance ? parseFloat(formData.billingBalance) : null,
            nextBillingDate: formData.nextBillingDate || null,
            id: Date.now() // Gerando um id único para o novo cartão
        };

        setCards(prevCards => [...prevCards, newCard]);
        handleCloseAddModal();
    };

    const handleDeleteCard = (id: number) => {
        setCards(prevCards => prevCards.filter(card => card.id !== id));
    };

    const handleOpenModalBankCard = (card: BankAccountsCards) => {
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
                {cards.length > 0 ? (
                    cards.map((card) => (
                        <BankCard
                            key={card.id}
                            bankName={card.bankName}
                            balance={card.balance}
                            nextBillingDate={card.nextBillingDate}
                            billingBalance={card.billingBalance}
                            onDelete={() => handleDeleteCard(card.id)} // Adicione esta linha para chamar a função handleDeleteCard com o ID da conta bancária
                            onOpenModal={() => handleOpenModalBankCard(card)} // Adicione esta linha para chamar a função handleOpenModalBankCard com os dados da conta bancária
                            id={card.id} // Adicione o ID da conta bancária como uma propriedade
                        />
                    ))
                ) : (
                    <p>Nenhum cartão encontrado.</p>
                )}
            </div>
            <TransactionListNoEdit period={''} />

            {/* Modal de adição de cartão */}
            <AddBankCardModal isOpen={isAddModalOpen} onClose={handleCloseAddModal} onAddCard={handleAddCard} formData={formData} handleInputChange={handleInputChange} />

            {/* Modal de detalhes do cartão */}
            {selectedBankCard && (
                <BankCardDetailsModal
                    isOpen={true}
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

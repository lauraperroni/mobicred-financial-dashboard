import React, { useEffect, useState } from 'react';
import BankCard from '../cards/BankCard';
import AddCard from '../buttons/AddCard';
import BankCardDetails from '../cards/BankCardDetails';
import AddBankCardModal from '../cards/AddBankCardModal';
import { BankAccountsService } from '../../services';
import LastTransactions from '../LastTransactions';
import TransactionListNoEdit from './TransactionListNoEdit';

interface BankAccountsCards {
    balance: number;
    bankName: string;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    id: number;
    userId: number;
    bankNumber: number;
}

const Accounts: React.FC = () => {
    const [cards, setCards] = useState<BankAccountsCards[]>([]);
    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<BankAccountsCards | null>(null);
    const [formData, setFormData] = useState({
        bankName: '',
        balance: '',
        nextBillingDate: '',
        billingBalance: '',
        accountType: '',
        bankNumber: 0 // Initialize as number, assuming it's taken from an input field
    });

    useEffect(() => {
        fetchAccounts();
    }, []);

    const fetchAccounts = async () => {
        try {
            const response = await BankAccountsService.getBankAccounts();
            if (response && response.status === 200) {
                const formattedData = response.data.map((item: any) => ({
                    ...item,
                    nextBillingDate: item.nextBillingDate || 'N/A',
                    billingBalance: item.billingBalance ?? null
                }));
                setCards(formattedData);
            } else {
                console.error('Erro ao buscar contas:', response);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
        }
    };

    const handleOpenAddModal = () => {
        setIsAddModalOpen(true);
    };

    const handleCloseAddModal = () => {
        setIsAddModalOpen(false);
    };

    const handleAddCard = async (formData: any) => {
        try {
            await BankAccountsService.postBankAccounts(formData);
            fetchAccounts();
            console.log('Conta bancária adicionada com sucesso');
        } catch (error) {
            console.error('Erro ao adicionar nova conta bancária:', error);
        }
    };

    const handleDeleteCard = async (id: number) => {
        try {
            await BankAccountsService.deleteBankAccounts(id);
            fetchAccounts();
            console.log('Conta bancária deletada com sucesso');
        } catch (error) {
            console.error('Erro ao deletar conta bancária:', error);
        }
    };

    const handleOpenModalBankCard = (card: BankAccountsCards) => {
        setSelectedBankCard(card);
        setFormData({
            bankName: card.bankName,
            balance: card.balance.toString(),
            nextBillingDate: card.nextBillingDate,
            billingBalance: card.billingBalance.toString(),
            accountType: card.accountType,
            bankNumber: card.bankNumber
        });
    };

    const handleCloseModalBankCard = () => {
        setSelectedBankCard(null);
    };

    const handleSaveChanges = async () => {
        try {
            if (!selectedBankCard) {
                console.error('Dados da conta bancária não encontrados.');
                return;
            }
            fetchAccounts(); // Atualiza a lista de contas bancárias após a atualização
            console.log('Conta bancária atualizada com sucesso');
            handleCloseModalBankCard();
        } catch (error) {
            console.error('Erro ao atualizar conta bancária:', error);
        }
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
                            onDelete={() => handleDeleteCard(card.id)}
                            onOpenModal={() => handleOpenModalBankCard(card)}
                            id={card.id}
                        />
                    ))
                ) : (
                    <p>Nenhum cartão encontrado.</p>
                )}
            </div>

            <TransactionListNoEdit period={''} />

            <AddBankCardModal isOpen={isAddModalOpen} onClose={handleCloseAddModal} onAddCard={handleAddCard} formData={formData} handleInputChange={handleInputChange} />

            {selectedBankCard && (
                <BankCardDetails
                    isOpen={true}
                    onCloseModal={handleCloseModalBankCard}
                    bankName={selectedBankCard.bankName}
                    balance={selectedBankCard.balance}
                    nextBillingDate={selectedBankCard.nextBillingDate}
                    billingBalance={selectedBankCard.billingBalance}
                    accountType={selectedBankCard.accountType}
                    id={selectedBankCard.id}
                    userId={selectedBankCard.userId}
                    bankNumber={selectedBankCard.bankNumber}
                    onSaveChanges={handleSaveChanges} // Passando a função handleSaveChanges
                />
            )}
        </>
    );
};

export default Accounts;

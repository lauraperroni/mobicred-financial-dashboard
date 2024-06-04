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

    const fetchAccounts = async () => {
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
    };

    useEffect(() => {
        fetchAccounts();
    }, []);

    const [isAddModalOpen, setIsAddModalOpen] = useState(false);
    const [selectedBankCard, setSelectedBankCard] = useState<BankAccountsCards | null>(null);

    const [formData, setFormData] = useState({
        bankName: '',
        balance: '',
        nextBillingDate: '',
        billingBalance: '',
        accountType: ''
    });

    const handleOpenAddModal = () => {
        setIsAddModalOpen(true);
    };

    const handleCloseAddModal = () => {
        setIsAddModalOpen(false);
    };

    const handleAddCard = async (formData: any) => {
        try {
            // Remove o campo id antes de enviar a requisição
            const { id, ...dataToSend } = formData;
            await BankAccountsService.postBankAccounts(dataToSend);
            // Recarrega a lista de contas após adicionar uma nova
            fetchAccounts();
            console.log('Conta bancária adicionada com sucesso');
        } catch (error) {
            console.error('Erro ao adicionar nova conta bancária:', error);
        }
    };

    const handleDeleteCard = async (id: number) => {
        try {
            await BankAccountsService.deleteBankAccounts(id);
            // Após a exclusão bem-sucedida, refaça a chamada dos dados do backend
            fetchAccounts();
        } catch (error) {
            console.error('Erro ao deletar conta bancária:', error);
        }
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
                            onDelete={() => handleDeleteCard(card.id)} // Chama handleDeleteCard com o ID da conta bancária
                            onOpenModal={() => handleOpenModalBankCard(card)} // Chama handleOpenModalBankCard com os dados da conta bancária
                            id={card.id} // Adiciona o ID da conta bancária como uma propriedade
                        />
                    ))
                ) : (
                    <p>Nenhum cartão encontrado.</p>
                )}
            </div>
            <TransactionListNoEdit />

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
                    accountType={selectedBankCard.accountType}
                />
            )}
        </>
    );
};

export default Accounts;

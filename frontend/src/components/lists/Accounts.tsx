// Accounts component
import React, { useEffect, useState } from 'react';
import BankCard from '../cards/BankCard';
import AddCard from '../buttons/AddCard';
import BankCardDetails from '../cards/BankCardDetails';
import AddBankCardModal from '../cards/AddBankCardModal';
import { BankAccountsService } from '../../services';

interface BankAccountsCards {
    balance: number;
    bankName: string;
    nextBillingDate: string;
    billingBalance: number;
    accountType: string;
    id: number; // Adicionando a propriedade id
    userId: number; // Adicionando a propriedade userId
    bankNumber: number; // Adicionando a propriedade bankNumber
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
        userId: 0
    });

    const fetchAccounts = async () => {
        try {
            const response = await BankAccountsService.getBankAccounts();
            if (response && response.status === 200) {
                console.log('Dados recebidos da API:', response.data);
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

    useEffect(() => {
        fetchAccounts();
    }, []);

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
    
    const handleSaveChanges = async () => {
        try {
            console.log('FormData:', formData); // Console.log do formData
            if (!selectedBankCard) {
                console.error('Dados da conta bancária não encontrados.');
                return;
            }
            const originalData = selectedBankCard; // Dados originais da conta bancária selecionada
            const updatedData = { ...originalData, ...formData }; // Combinando dados originais com dados do formulário
    
            // Enviar solicitação PUT com todos os dados do cartão
            await BankAccountsService.putBankAccounts(originalData.id, updatedData);
    
            fetchAccounts();
            console.log('Conta bancária atualizada com sucesso');
            handleCloseModalBankCard();
        } catch (error) {
            console.error('Erro ao atualizar conta bancária:', error);
        }
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
                    onSaveChanges={handleSaveChanges}
                />
            )}
        </>
    );
};

export default Accounts;

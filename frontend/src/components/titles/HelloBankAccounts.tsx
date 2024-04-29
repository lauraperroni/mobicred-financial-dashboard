
interface BankAccountsProps {
    userName: string;
}

function HelloBankAccounts({ userName }: BankAccountsProps) {

    return (
        <>
            <h1 className="text-3xl leading-10 text-gray-900 m-8">
                Your Bank Accounts, {userName}!
            </h1>
        </>
    );
}
export default HelloBankAccounts;
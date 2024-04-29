
interface HelloUserProps {
    userName: string;
}

function HelloUser({ userName }: HelloUserProps) {

    return (
        <>
            <h1 className="text-3xl leading-10 text-gray-900 m-8">
                Hello, {userName}!
            </h1>
        </>
    );
}
export default HelloUser;
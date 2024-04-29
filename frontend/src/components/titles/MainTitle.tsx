
interface MainTitleProps {
    title: string;
}

function MainTitle({ title }: MainTitleProps) {

    return (
        <>
            <h1 className="text-3xl leading-10 text-gray-900 m-8">
                {title}
            </h1>
        </>
    );
}
export default MainTitle;
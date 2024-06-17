import React from 'react';
import Header from "../../components/Header";
import CalculateCompoundInterest from "../../components/calculators/CalculateCompoundInterest";

const CompoundInterest: React.FC = () => {
    return (
        <>
            <Header />
            <div className="custom-bg">
                <CalculateCompoundInterest />
            </div>
        </>
    );
};

export default CompoundInterest;

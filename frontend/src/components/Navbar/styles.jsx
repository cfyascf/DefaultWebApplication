import styled from 'styled-components';

const NavbarContainer = styled.div `
    width: 100%;
    heigth: 10vh;
    display: flex;
`;

const CompanyLogo = styled.div `
    display: flex;
    justify-content: center;
    align-items: center;
`;

const CompanyName = styled.div `
    font-size: 1rem;
    font-weight: 500;
`;

const UserOptions = styled.div `
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
`;

export { CompanyLogo, NavbarContainer, UserOptions, CompanyName };
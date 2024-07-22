import styled from 'styled-components';

const NavbarContainer = styled.div `
    width: 100%;
    height: fit-content;
    display: flex;
    justify-content: space-between;
    padding: 15px;
    box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
`;

const CompanyBranding = styled.div `
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 10px;
`;

const CompanyName = styled.h1 `
    font-size: 1rem;
    font-weight: 600;
`;

const Icon = styled.img `
    width: 30px;
    height: 30px;
    object-fit: cover;
`;

const UserOptions = styled.div `
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 10px;
`;

export { CompanyBranding, NavbarContainer, UserOptions, CompanyName, Icon };
-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tempo de Geração: 20/07/2014 às 22h24min
-- Versão do Servidor: 5.5.37
-- Versão do PHP: 5.3.10-1ubuntu3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `protegemed`
--

CREATE DATABASE  IF NOT EXISTS `teste`;
USE `teste`;

DELIMITER $$
--
-- Funções
--
DROP FUNCTION IF EXISTS `f_ultCodCaptura`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `f_ultCodCaptura`(sala INT, equip INT) RETURNS int(11)
BEGIN
	declare ultimo int;

	-- encontra o ultimo codigo de captura de um equipamento numa sala ativa
	select max(cap.codcaptura) into @ultimo
	from capturaatual cap, usosalacaptura usc
	where cap.codequip = equip
	and usc.codcaptura = cap.codcaptura
	and usc.codusosala = f_ultUsoSalaAtiva(sala);

	if (@ultimo is null) then
        set @ultimo = 0;
	end if;

	return @ultimo;
RETURN 1;
END$$

DROP FUNCTION IF EXISTS `f_ultUsoSala`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `f_ultUsoSala`(sala INT) RETURNS int(11)
BEGIN
	declare ultimo int;

	select max(u.codUsoSala) into @ultimo
	from usosala u
	where u.codSala = sala;

	if (@ultimo is null) then
		select AUTO_INCREMENT into @ultimo
		from information_schema.TABLES			 
		where TABLE_SCHEMA = 'protegemed'
		and   TABLE_NAME = 'usosala';
	end if;

	return @ultimo;
END$$

DROP FUNCTION IF EXISTS `f_ultUsoSalaAtiva`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `f_ultUsoSalaAtiva`(sala INT) RETURNS int(11)
BEGIN
	declare ultimo int;

	select max(codusosala) into @ultimo
	from usosala uso
	where ativa = 1
	and codsala = sala;

	if (@ultimo is null) then
        set @ultimo = 0;
	end if;

	return @ultimo;
RETURN 1;
END$$

DROP FUNCTION IF EXISTS `nextCaptura`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nextCaptura`() RETURNS int(11)
BEGIN
	declare ultimo INT;

	select max(codcaptura) into @ultimo
    from capturaatual ;
	
	return @ultimo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `equipamento`
--

DROP TABLE IF EXISTS `equipamento`;
CREATE TABLE IF NOT EXISTS `equipamento` (
  `codEquip` int(11) NOT NULL AUTO_INCREMENT,
  `codMarca` int(11) NOT NULL,
  `codModelo` int(11) NOT NULL,
  `codTipo` int(11) NOT NULL,
  `rfid` varchar(45) NOT NULL,
  `codPatrimonio` int(11) NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `dataUltimaFalha` date DEFAULT NULL,
  `dataUltimaManutencao` date DEFAULT NULL,
  `tempoUso` time DEFAULT NULL,
  PRIMARY KEY (`codEquip`),
  KEY `codMarca` (`codMarca`),
  KEY `codTipo` (`codTipo`),
  KEY `codModelo` (`codModelo`),
  KEY `fk_equipamento_1_idx` (`codTipo`),
  KEY `fk_equipamento_2_idx` (`codMarca`),
  KEY `fk_equipamento_3_idx` (`codModelo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `equipamento`
--

INSERT INTO `equipamento` (`codEquip`, `codMarca`, `codModelo`, `codTipo`, `rfid`, `codPatrimonio`, `desc`, `dataUltimaFalha`, `dataUltimaManutencao`, `tempoUso`) VALUES
(1, 1, 1, 1, '12345678', 0, 'Equipamento Padrão (+30)', '2012-03-01', '2012-03-01', '07:14:06'),
(2, 1, 1, 1, '12345601', 1, 'Equipamento na tomada 1', '2012-03-01', '2012-03-01', '03:49:12'),
(3, 1, 1, 1, '12345602', 2, 'Equipamento na tomada 2', '2012-03-01', '2012-03-01', '00:07:09'),
(4, 1, 1, 1, '12345603', 3, 'Equipamento na tomada 3', '2012-03-01', '2012-03-01', '00:06:36'),
(5, 1, 1, 1, '12345604', 4, 'Equipamento na tomada 4', '2012-03-01', '2012-03-01', '00:02:34'),
(6, 1, 1, 1, '12345605', 5, 'Equipamento na tomada 5', '2012-03-01', '2012-03-01', '00:01:22'),
(7, 1, 1, 1, '12345606', 6, 'Equipamento na tomada 6', '2012-03-01', '2012-03-01', '00:00:10');

-- --------------------------------------------------------

--
-- Estrutura da tabela `eventos`
--

DROP TABLE IF EXISTS `eventos`;
CREATE TABLE IF NOT EXISTS `eventos` (
  `codEvento` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) NOT NULL,
  `formaOnda` tinyint(4) NOT NULL,
  PRIMARY KEY (`codEvento`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `eventos`
--

INSERT INTO `eventos` (`codEvento`, `desc`, `formaOnda`) VALUES
(1, 'Evento de Fuga', 1),
(2, 'Acompanhamento - Fase', 1),
(3, 'Acompanhamento - Diferencial', 1),
(4, 'Equipamento Ligado', 1),
(5, 'Equipamento Desligado', 0),
(6, 'Término de Fuga', 0),
(7, 'Oi', 0),
(8, 'Protegemed Inicializando', 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `marca`
--

DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `codMarca` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codMarca`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `marca`
--

INSERT INTO `marca` (`codMarca`, `desc`) VALUES
(1, 'Marca Padrão'),
(2, 'TESTEEEEE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `modelo`
--

DROP TABLE IF EXISTS `modelo`;
CREATE TABLE IF NOT EXISTS `modelo` (
  `codModelo` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codModelo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `modelo`
--

INSERT INTO `modelo` (`codModelo`, `desc`) VALUES
(1, 'Modelo Padrão');

-- --------------------------------------------------------

--
-- Estrutura da tabela `procedimento`
--

DROP TABLE IF EXISTS `procedimento`;
CREATE TABLE IF NOT EXISTS `procedimento` (
  `codProced` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codProced`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `procedimento`
--

INSERT INTO `procedimento` (`codProced`, `desc`) VALUES
(1, 'teste'),
(6, 'opaaa'),
(7, 'opaaa');

-- --------------------------------------------------------

--
-- Estrutura da tabela `responsavel`
--

DROP TABLE IF EXISTS `responsavel`;
CREATE TABLE IF NOT EXISTS `responsavel` (
  `codResp` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codResp`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Extraindo dados da tabela `responsavel`
--

INSERT INTO `responsavel` (`codResp`, `desc`) VALUES
(1, 'teste'),
(9, 'jose'),
(10, 'jose');

-- --------------------------------------------------------

--
-- Estrutura da tabela `salacirurgia`
--

DROP TABLE IF EXISTS `salacirurgia`;
CREATE TABLE IF NOT EXISTS `salacirurgia` (
  `codSala` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codSala`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `salacirurgia`
--

INSERT INTO `salacirurgia` (`codSala`, `desc`) VALUES
(1, 'Sala de Testes'),
(2, 'Sala dois'),
(3, 'Sala tres'),
(4, 'Sala quatro'),
(5, 'Sala cinco');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipo`
--

DROP TABLE IF EXISTS `tipo`;
CREATE TABLE IF NOT EXISTS `tipo` (
  `codTipo` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codTipo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Extraindo dados da tabela `tipo`
--

INSERT INTO `tipo` (`codTipo`, `desc`) VALUES
(1, 'Fase'),
(2, 'Fuga'),
(7, 'TESTEEEEE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipoonda`
--

DROP TABLE IF EXISTS `tipoonda`;
CREATE TABLE IF NOT EXISTS `tipoonda` (
  `codTipoOnda` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codTipoOnda`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `tipoonda`
--

INSERT INTO `tipoonda` (`codTipoOnda`, `desc`) VALUES
(1, 'Fase'),
(2, 'Fuga');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tipopadrao`
--

DROP TABLE IF EXISTS `tipopadrao`;
CREATE TABLE IF NOT EXISTS `tipopadrao` (
  `codTipoPadrao` int(11) NOT NULL AUTO_INCREMENT,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codTipoPadrao`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `tipopadrao`
--

INSERT INTO `tipopadrao` (`codTipoPadrao`, `desc`) VALUES
(1, 'Funcionamento Normal'),
(2, 'Erro - Fuga de corrente'),
(4, 'oy');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tomada`
--

DROP TABLE IF EXISTS `tomada`;
CREATE TABLE IF NOT EXISTS `tomada` (
  `codTomada` int(11) NOT NULL,
  `codSala` int(11) NOT NULL,
  `indice` int(11) DEFAULT NULL,
  `modulo` int(11) DEFAULT NULL,
  `desc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codTomada`),
  KEY `codUsoSala3` (`codSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `tomada`
--

INSERT INTO `tomada` (`codTomada`, `codSala`, `indice`, `modulo`, `desc`) VALUES
(1, 1, 1, 1, 'Tomada 1'),
(2, 1, 2, 2, 'Tomada 2'),
(3, 1, 3, 3, 'Tomada 3'),
(4, 1, 4, 4, 'Tomada 4'),
(5, 1, 5, 5, 'Tomada 5'),
(6, 1, 6, 6, 'Tomada 6'),
(7, 3, 7, 7, 'Tomada 7');

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `equipamento`
--
ALTER TABLE `equipamento`
  ADD CONSTRAINT `fk_equipamento_1` FOREIGN KEY (`codTipo`) REFERENCES `tipo` (`codTipo`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_equipamento_2` FOREIGN KEY (`codMarca`) REFERENCES `marca` (`codMarca`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_equipamento_3` FOREIGN KEY (`codModelo`) REFERENCES `modelo` (`codModelo`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para a tabela `tomada`
--
ALTER TABLE `tomada`
  ADD CONSTRAINT `codSala2` FOREIGN KEY (`codSala`) REFERENCES `salacirurgia` (`codSala`) ON DELETE CASCADE ON UPDATE NO ACTION;

-- --------------------------------------------------------

--
-- Estrutura da tabela `capturaatual`
--

DROP TABLE IF EXISTS `capturaatual`;
CREATE TABLE IF NOT EXISTS `capturaatual` (
  `codCaptura` int(11) NOT NULL AUTO_INCREMENT,
  `codTomada` int(11) NOT NULL,
  `codTipoOnda` int(11) NOT NULL,
  `codEquip` int(11) NOT NULL,
  `codEvento` int(11) NOT NULL,
  `valorMedio` float DEFAULT NULL,
  `offset` float DEFAULT NULL,
  `gain` float DEFAULT NULL,
  `eficaz` float DEFAULT NULL,
  `dataAtual` datetime DEFAULT NULL,
  `VM2` float DEFAULT NULL,
  `under` int(11) DEFAULT NULL,
  `over` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`codCaptura`),
  KEY `codTomada2` (`codTomada`),
  KEY `codEquipamento2` (`codEquip`),
  KEY `codTipoOnda2` (`codTipoOnda`),
  KEY `codEvento` (`codEvento`),
  KEY `fk_capturaatual_1_idx` (`codEvento`),
  KEY `fk_capturaatual_2_idx` (`codTipoOnda`),
  KEY `fk_capturaatual_3_idx` (`codEquip`),
  KEY `fk_capturaatual_4_idx` (`codTomada`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6222534 ;

--
-- Gatilhos `capturaatual`
--
DROP TRIGGER IF EXISTS `atualiza`;
DELIMITER //
CREATE TRIGGER `atualiza` AFTER INSERT ON `capturaatual`
 FOR EACH ROW begin
	declare ultUsoSala    int default 0; /* = seleciona a sala q esta em uso referente a sala recebida na ultima onda recebida.*/
	declare salaAtual     int default 0; /* armazena a sala da inclusao atual */
	declare usoSalaAtiva  int default 0; /* armazena o estado do campo ativa em uso sala na última usosala */
	declare insEquip      int default 0; /* conta se o equipamento ja foi inserido em usosalequip */
	declare existeUsoSala int default 0; /* se já foi inserido o usosala */
	declare capturaLiga   int default 0; /* codigo da captura que ligou um equipamento */
	declare horaLiga	  time;          /* hora em que o equipamento foi ligado */
	declare onEquip       int default 0; /* numero de aparelhos ligados num usosala */
	declare offEquip	  int default 0; /* numero de aparelhos desligados num usosla */

	/* pega a sala da inserção sendo realizada e guarda em salaAtual */
	select tom.codSala into @salaAtual
	from tomada tom
	where new.codTomada = tom.codTomada;

	/* pega a última codigo do último UsoSala da sala onde esta sendo inserida a onda */
	set @ultUsoSala = f_ultUsoSala( @salaAtual );
	
	/* verifica se a usosala esta ativa */
	select ativa into @usoSalaAtiva
	from usosala 
	where codUsoSala = @ultUsoSala;

	/* verifica se existe usosala */
	select count(*) into @existeUsoSala 
	from usosala 
	where codUsoSala = @ultUsoSala;
	
	/* caso não exista um uso sala ou o usoSala não ativo, cria um novo */
	if (@usoSalaAtiva = 0 or @existeUsoSala = 0) then 
		insert into usosala (codsala, codProced, codResp, horainicio,   ativa) 
		values (          @salaAtual,         1,       1, new.dataAtual, 1); 

		set @ultUsoSala = f_ultUsoSala( @salaAtual );		
	end if;

	/* Insere o evento de captura em usosalacaptura */
	insert into usosalacaptura (codUsoSala, codCaptura) values (@ultUsoSala, new.codCaptura); 

	/* Verifica se o equipamento já foi inserido */
	select count(*) into @insEquip
	from usosalaequip ue
	where ue.codEquip = new.codEquip
	and   ue.codUsoSala = @ultUsoSala;

	if (@insEquip = 0) then
		/* Insere o equipamento no usosalaequip */
		insert into usosalaequip (codUsoSala, codEquip) values (@ultUsoSala, new.codEquip);
	end if;

	if (new.codEvento = 5) then /* Equipamento sendo desligado */
		/* último evento que ligou o equipamento */ 
		select max(codCaptura) into @capturaLiga
		from capturaatual 
		where codEvento = 4               /* Equipamento sendo ligado */		
		and codEquip = new.codEquip;
		
		select time(dataAtual) into @horaLiga
		from capturaatual
		where codcaptura = @capturaLiga;		

		update equipamento
		set tempoUso = addtime( tempoUso, subtime(time(new.dataAtual), @horaLiga) )
		where codEquip = new.codEquip;

		select count(*) into @onEquip
		from capturaatual cap, usosalacaptura uso
		where cap.codCaptura = uso.codCaptura
		and   cap.codEvento = 4
		and   uso.codUsoSala = @ultUsoSala;

		select count(*) into @offEquip
		from capturaatual cap, usosalacaptura uso
		where cap.codCaptura = uso.codCaptura
		and   cap.codEvento = 5
		and   uso.codUsoSala = @ultUsoSala;

		if (@onEquip = @offEquip) then
			update usosala
			set horaFinal = new.dataAtual, ativa = 0
			where codusosala = @ultUsoSala;
		end if; /* @onEquip = @offEquip */
	end if;     /* Equipamento sendo desligado */
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `harmatual`
--

DROP TABLE IF EXISTS `harmatual`;
CREATE TABLE IF NOT EXISTS `harmatual` (
  `codCaptura` int(11) NOT NULL,
  `codHarmonica` int(11) NOT NULL,
  `sen` float DEFAULT NULL,
  `cos` float DEFAULT NULL,
  PRIMARY KEY (`codCaptura`,`codHarmonica`),
  KEY `codOndaAtual` (`codCaptura`),
  KEY `fk_ondaatual_1_idx` (`codCaptura`),
  KEY `fk_ondaatual_1_idx1` (`codCaptura`),
  KEY `fk_harmatual_1_idx` (`codCaptura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `harmpadrao`
--

DROP TABLE IF EXISTS `harmpadrao`;
CREATE TABLE IF NOT EXISTS `harmpadrao` (
  `codHarmonica` int(11) NOT NULL,
  `codOndaPadrao` int(11) NOT NULL,
  `sen` float DEFAULT NULL,
  `cos` float DEFAULT NULL,
  PRIMARY KEY (`codHarmonica`,`codOndaPadrao`),
  KEY `codOndaP` (`codOndaPadrao`),
  KEY `fk_harmpadrao_1_idx` (`codOndaPadrao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ondapadrao`
--

DROP TABLE IF EXISTS `ondapadrao`;
CREATE TABLE IF NOT EXISTS `ondapadrao` (
  `codOndaPadrao` int(11) NOT NULL AUTO_INCREMENT,
  `codTipoOnda` int(11) NOT NULL,
  `codTomada` int(11) NOT NULL,
  `codEquip` int(11) NOT NULL,
  `valorMedio` float DEFAULT NULL,
  `offset` float DEFAULT NULL,
  `gain` float DEFAULT NULL,
  `eficaz` float DEFAULT NULL,
  `dataPadrao` datetime DEFAULT NULL,
  `codTipoPadrao` int(11) NOT NULL,
  PRIMARY KEY (`codOndaPadrao`),
  KEY `codEquip` (`codEquip`),
  KEY `codTomada` (`codTomada`),
  KEY `codTipoOnda` (`codTipoOnda`),
  KEY `fk_ondapadrao_1_idx` (`codTipoOnda`),
  KEY `fk_ondapadrao_2_idx` (`codTipoPadrao`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usosala`
--

DROP TABLE IF EXISTS `usosala`;
CREATE TABLE IF NOT EXISTS `usosala` (
  `codUsoSala` int(11) NOT NULL AUTO_INCREMENT,
  `codSala` int(11) NOT NULL,
  `codProced` int(11) NOT NULL,
  `codResp` int(11) NOT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horaFinal` datetime DEFAULT NULL,
  `ativa` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codUsoSala`),
  KEY `codResp` (`codResp`),
  KEY `codProced` (`codProced`),
  KEY `codSala` (`codSala`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=126 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usosalacaptura`
--

DROP TABLE IF EXISTS `usosalacaptura`;
CREATE TABLE IF NOT EXISTS `usosalacaptura` (
  `codCaptura` int(11) NOT NULL,
  `codUsoSala` int(11) NOT NULL,
  PRIMARY KEY (`codCaptura`,`codUsoSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usosalaequip`
--

DROP TABLE IF EXISTS `usosalaequip`;
CREATE TABLE IF NOT EXISTS `usosalaequip` (
  `codEquip` int(11) NOT NULL,
  `codUsoSala` int(11) NOT NULL,
  PRIMARY KEY (`codEquip`,`codUsoSala`),
  KEY `codEquip3` (`codEquip`),
  KEY `codUsoSala` (`codUsoSala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `capturaatual`
--
ALTER TABLE `capturaatual`
  ADD CONSTRAINT `fk_capturaatual_1` FOREIGN KEY (`codEvento`) REFERENCES `eventos` (`codEvento`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_2` FOREIGN KEY (`codTipoOnda`) REFERENCES `tipoonda` (`codTipoOnda`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_3` FOREIGN KEY (`codEquip`) REFERENCES `equipamento` (`codEquip`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_capturaatual_4` FOREIGN KEY (`codTomada`) REFERENCES `tomada` (`codTomada`) ON UPDATE NO ACTION;

--
-- Restrições para a tabela `harmatual`
--
ALTER TABLE `harmatual`
  ADD CONSTRAINT `fk_harmatual_1` FOREIGN KEY (`codCaptura`) REFERENCES `capturaatual` (`codCaptura`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para a tabela `harmpadrao`
--
ALTER TABLE `harmpadrao`
  ADD CONSTRAINT `fk_harmpadrao_1` FOREIGN KEY (`codOndaPadrao`) REFERENCES `ondapadrao` (`codOndaPadrao`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para a tabela `ondapadrao`
--
ALTER TABLE `ondapadrao`
  ADD CONSTRAINT `codEquip` FOREIGN KEY (`codEquip`) REFERENCES `equipamento` (`codEquip`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `codTomada` FOREIGN KEY (`codTomada`) REFERENCES `tomada` (`codTomada`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ondapadrao_1` FOREIGN KEY (`codTipoOnda`) REFERENCES `tipoonda` (`codTipoOnda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ondapadrao_2` FOREIGN KEY (`codTipoPadrao`) REFERENCES `tipopadrao` (`codTipoPadrao`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Restrições para a tabela `usosala`
--
ALTER TABLE `usosala`
  ADD CONSTRAINT `codProced` FOREIGN KEY (`codProced`) REFERENCES `procedimento` (`codProced`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `codResp` FOREIGN KEY (`codResp`) REFERENCES `responsavel` (`codResp`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `codSala` FOREIGN KEY (`codSala`) REFERENCES `salacirurgia` (`codSala`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
